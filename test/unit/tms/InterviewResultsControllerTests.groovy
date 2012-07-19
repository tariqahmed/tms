package tms



import org.junit.*
import grails.test.mixin.*

@TestFor(InterviewResultsController)
@Mock(InterviewResults)
class InterviewResultsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/interviewResults/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.interviewResultsInstanceList.size() == 0
        assert model.interviewResultsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.interviewResultsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.interviewResultsInstance != null
        assert view == '/interviewResults/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/interviewResults/show/1'
        assert controller.flash.message != null
        assert InterviewResults.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/interviewResults/list'

        populateValidParams(params)
        def interviewResults = new InterviewResults(params)

        assert interviewResults.save() != null

        params.id = interviewResults.id

        def model = controller.show()

        assert model.interviewResultsInstance == interviewResults
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/interviewResults/list'

        populateValidParams(params)
        def interviewResults = new InterviewResults(params)

        assert interviewResults.save() != null

        params.id = interviewResults.id

        def model = controller.edit()

        assert model.interviewResultsInstance == interviewResults
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/interviewResults/list'

        response.reset()

        populateValidParams(params)
        def interviewResults = new InterviewResults(params)

        assert interviewResults.save() != null

        // test invalid parameters in update
        params.id = interviewResults.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/interviewResults/edit"
        assert model.interviewResultsInstance != null

        interviewResults.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/interviewResults/show/$interviewResults.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        interviewResults.clearErrors()

        populateValidParams(params)
        params.id = interviewResults.id
        params.version = -1
        controller.update()

        assert view == "/interviewResults/edit"
        assert model.interviewResultsInstance != null
        assert model.interviewResultsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/interviewResults/list'

        response.reset()

        populateValidParams(params)
        def interviewResults = new InterviewResults(params)

        assert interviewResults.save() != null
        assert InterviewResults.count() == 1

        params.id = interviewResults.id

        controller.delete()

        assert InterviewResults.count() == 0
        assert InterviewResults.get(interviewResults.id) == null
        assert response.redirectedUrl == '/interviewResults/list'
    }
}
