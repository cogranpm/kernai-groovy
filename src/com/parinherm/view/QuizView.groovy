/*
 * implements view for saving a single question
 * going to test out jface databinding to a groovy map
 * 
 * current best way to do this is store json column in database which can 
 * convert to a groovy map easily, which can be converted easily to 
 * jface writableMap and observed easily
 * no domain class required, no mapping, always the same sql query
 * catch is you can't query fields at database level
 *  
https://stackoverflow.com/questions/31560084/jface-databinding-map-property-to-swt-text-field
Finally found it... map entries can be observed via

IObservableValue o = Observables.observeMapEntry(map, "test")
 * 
 * 
 * 
 * problem with maps is that haven't figured out how to observe a list of maps
 * in the tableviewer, to get that nice table updates as edit field is changed
 * groovy does however allow map based pojo construction, and also a single line 
 * output pojo to json
 * 
 * package com.example.groovy
import groovy.json.JsonBuilder  
import groovy.json.JsonSlurper  
import groovy.transform.ToString

class JsonToObject {
    public static void main(String[] args) {
        // Person object
        def person = new Person(firstName: "John", lastName: "Doe")
        // Json String
        def personJSON = new JsonBuilder(person).toPrettyString()
        // Json String to Map
        def personMap = new JsonSlurper().parseText(personJSON)
        // using Map to convert to Person object type
        def newPerson = new Person(personMap)
        println(person)
        println(newPerson)
        assert newPerson.firstName.equals(person.firstName)
        assert newPerson.lastName.equals(person.lastName)
    }
}

 * 
 * 
 */


package com.parinherm.view

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter

import org.eclipse.core.databinding.AggregateValidationStatus
import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.UpdateValueStrategy
import org.eclipse.core.databinding.beans.typed.BeanProperties
import org.eclipse.core.databinding.observable.list.IObservableList
import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.core.databinding.observable.value.WritableValue
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text

import com.parinherm.domain.Question
import com.parinherm.validators.CompoundValidator
import com.parinherm.validators.EmptyStringValidator

import groovy.json.JsonGenerator
import groovy.json.JsonSlurper

//todo remove this
import groovy.sql.Sql
import java.util.logging.*


class QuizView extends Composite{
	//def props = [id:0, questionText:'', answerText:'']
	DataBindingContext dbc = new DataBindingContext()
	Text txtId
	Label lblError
	WritableValue value = new WritableValue()
	//WritableMap wm = new WritableMap()
	Question model

	JsonGenerator jsonOutputter = new JsonGenerator.Options().excludeFieldsByName('propertyChangeListeners').build()
	
	
	QuizView(Composite parent){
		super(parent, SWT.NONE)
	
		Logger.getLogger('groovy.sql').level = Level.FINE
			
		//go from json to a domain entity 
		String jsonQuestion = '{"id":0, "question": "what is the color of postassium", "category": "general", "answer": "yellow"}'
		//json comes in raw from the database
		def questionMap = new JsonSlurper().parseText(jsonQuestion)
		//domain entity is constructed from the parsed json map
		model = new Question(questionMap)
		value.setValue(model)
		//domain entity is then bound to the ui controls
		
		//this is map based stuff
		//wm.putAll(questionMap)
		//value.setValue(wm)
		
		lblError = new Label(this, SWT.NONE)
		Label lblId = new Label(this, SWT.NONE)
		lblId.text = "Question"
		txtId = new Text(this, SWT.NONE)
		
		Button btnDo = new Button(this, SWT.PUSH)
		btnDo.text = "Save"
		btnDo.addSelectionListener(widgetSelectedAdapter{
			persist()
		}) 
		
		/*
		IObservableValue target =  WidgetProperties.text(SWT.Modify).observe(txtId)
		IObservableValue model = Observables.observeMapEntry(wm, "question")
		dbc.bindValue(target, model)
		*/
		addBindings()
		
		GridDataFactory.fillDefaults().span(2, 1).applyTo(lblError)
		GridDataFactory.fillDefaults().applyTo(lblId)
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtId)
		GridDataFactory.fillDefaults().applyTo(btnDo)
		this.setLayout(new GridLayout(2, false))
	}
	
	private def addBindings() {
		dbc.dispose()
		IObservableList dabindings = dbc.getValidationStatusProviders()
		dabindings.each { element ->
			def b = element as Binding
			dbc.removeBinding(b)
		}
		final IObservableValue controlObservable = WidgetProperties.text(SWT.Modify).observe(txtId)
		final IObservableValue modelObservable = BeanProperties.value("question").observeDetail(value)
		
		
		// create a validators library
		//create a validation class for each unique validation
		
		// look to this link:
		//https://eclipsesource.com/blogs/2012/08/22/improving-reuse-of-jface-data-binding-validators/
		/*
		 * The solution is to create an IValidator implementation whose only job is to execute a list of validators one after the other and return their aggregated results:
		*/

		final UpdateValueStrategy updateStrategy = new UpdateValueStrategy().tap { 
			afterConvertValidator = new CompoundValidator(new EmptyStringValidator("Question"))
		}
  
		def binding = dbc.bindValue(controlObservable, modelObservable, updateStrategy, null)
		def errorDecorator = ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT)
		
		// error label binding
		final IObservableValue errorObservable = WidgetProperties.text().observe(lblError)
		def allValidationBinding = dbc.bindValue(errorObservable, new AggregateValidationStatus(dbc.getBindings(), AggregateValidationStatus.MAX_SEVERITY), null, null);

	}
	
	private def persist() {
		def url = 'jdbc:h2:~/kernaidb'
		def user = 'sa'
		def password = ''
		def driver = 'org.h2.Driver'
		def sql = Sql.newInstance(url, user, password, driver)
		def tableddl = """\
					CREATE TABLE IF NOT EXISTS ENTITYDATA(ID IDENTITY NOT NULL PRIMARY KEY,
					ENTITYCLASS VARCHAR NOT NULL,
					DATA JSON NOT NULL);""".stripIndent()
		sql.execute(tableddl)
		def clazz = model.getClass().getName()
		def json = jsonOutputter.toJson(model)
		
		def insert = """\
			INSERT INTO ENTITYDATA (ENTITYCLASS, DATA) VALUES (?, ? FORMAT JSON);
		""".stripIndent()
		sql.execute insert, [clazz, json]

		//define a list of questions
		def list = []
		Closure rowParser = {row -> 
			def id = row[0]
			def clazzName = row[1]
			def jsonRaw = new String(row[2])
			def questionMap = new JsonSlurper().parseText(jsonRaw)
			def question = new Question(questionMap)
			question.id = id
			list << question
		}

		sql.eachRow('SELECT ID, ENTITYCLASS, DATA FORMAT JSON FROM ENTITYDATA', rowParser) 
		println list
 
		
		sql.close()
	}
}
