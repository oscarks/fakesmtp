package com.acception.fakesmtp



import org.junit.*
import grails.test.mixin.*

@TestFor(FmMailMessageController)
@Mock(FmMailMessage)
class FmMailMessageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fmMailMessage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fmMailMessageInstanceList.size() == 0
        assert model.fmMailMessageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fmMailMessageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fmMailMessageInstance != null
        assert view == '/fmMailMessage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fmMailMessage/show/1'
        assert controller.flash.message != null
        assert FmMailMessage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fmMailMessage/list'

        populateValidParams(params)
        def fmMailMessage = new FmMailMessage(params)

        assert fmMailMessage.save() != null

        params.id = fmMailMessage.id

        def model = controller.show()

        assert model.fmMailMessageInstance == fmMailMessage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fmMailMessage/list'

        populateValidParams(params)
        def fmMailMessage = new FmMailMessage(params)

        assert fmMailMessage.save() != null

        params.id = fmMailMessage.id

        def model = controller.edit()

        assert model.fmMailMessageInstance == fmMailMessage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fmMailMessage/list'

        response.reset()

        populateValidParams(params)
        def fmMailMessage = new FmMailMessage(params)

        assert fmMailMessage.save() != null

        // test invalid parameters in update
        params.id = fmMailMessage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fmMailMessage/edit"
        assert model.fmMailMessageInstance != null

        fmMailMessage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fmMailMessage/show/$fmMailMessage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fmMailMessage.clearErrors()

        populateValidParams(params)
        params.id = fmMailMessage.id
        params.version = -1
        controller.update()

        assert view == "/fmMailMessage/edit"
        assert model.fmMailMessageInstance != null
        assert model.fmMailMessageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fmMailMessage/list'

        response.reset()

        populateValidParams(params)
        def fmMailMessage = new FmMailMessage(params)

        assert fmMailMessage.save() != null
        assert FmMailMessage.count() == 1

        params.id = fmMailMessage.id

        controller.delete()

        assert FmMailMessage.count() == 0
        assert FmMailMessage.get(fmMailMessage.id) == null
        assert response.redirectedUrl == '/fmMailMessage/list'
    }
}
