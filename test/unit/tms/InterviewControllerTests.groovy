package tms



import org.junit.*
import grails.test.mixin.*

@TestFor(InterviewController)
@Mock(Interview)
class InterviewControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/interview/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.interviewInstanceList.size() == 0
        assert model.interviewInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.interviewInstance != null
    }

    void testSave() {
        controller.save()

        assert model.interviewInstance != null
        assert view == '/interview/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/interview/show/1'
        assert controller.flash.message != null
        assert Interview.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/interview/list'

        populateValidParams(params)
        def interview = new Interview(params)

        assert interview.save() != null

        params.id = interview.id

        def model = controller.show()

        assert model.interviewInstance == interview
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/interview/list'

        populateValidParams(params)
        def interview = new Interview(params)

        assert interview.save() != null

        params.id = interview.id

        def model = controller.edit()

        assert model.interviewInstance == interview
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/interview/list'

        response.reset()

        populateValidParams(params)
        def interview = new Interview(params)

        assert interview.save() != null

        // test invalid parameters in update
        params.id = interview.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/interview/edit"
        assert model.interviewInstance != null

        interview.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/interview/show/$interview.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        interview.clearErrors()

        populateValidParams(params)
        params.id = interview.id
        params.version = -1
        controller.update()

        assert view == "/interview/edit"
        assert model.interviewInstance != null
        assert model.interviewInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/interview/list'

        response.reset()

        populateValidParams(params)
        def interview = new Interview(params)

        assert interview.save() != null
        assert Interview.count() == 1

        params.id = interview.id

        controller.delete()

        assert Interview.count() == 0
        assert Interview.get(interview.id) == null
        assert response.redirectedUrl == '/interview/list'
    }
}
