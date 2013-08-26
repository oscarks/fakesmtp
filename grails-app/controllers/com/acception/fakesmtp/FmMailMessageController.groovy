package com.acception.fakesmtp

import org.springframework.dao.DataIntegrityViolationException

class FmMailMessageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fmMailMessageInstanceList: FmMailMessage.list(params), fmMailMessageInstanceTotal: FmMailMessage.count()]
    }

    def create() {
        [fmMailMessageInstance: new FmMailMessage(params)]
    }

    def save() {
        def fmMailMessageInstance = new FmMailMessage(params)
        if (!fmMailMessageInstance.save(flush: true)) {
            render(view: "create", model: [fmMailMessageInstance: fmMailMessageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), fmMailMessageInstance.id])
        redirect(action: "show", id: fmMailMessageInstance.id)
    }

    def show(Long id) {
        def fmMailMessageInstance = FmMailMessage.get(id)
        if (!fmMailMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), id])
            redirect(action: "list")
            return
        }

        [fmMailMessageInstance: fmMailMessageInstance]
    }

    def edit(Long id) {
        def fmMailMessageInstance = FmMailMessage.get(id)
        if (!fmMailMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), id])
            redirect(action: "list")
            return
        }

        [fmMailMessageInstance: fmMailMessageInstance]
    }

    def update(Long id, Long version) {
        def fmMailMessageInstance = FmMailMessage.get(id)
        if (!fmMailMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fmMailMessageInstance.version > version) {
                fmMailMessageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'fmMailMessage.label', default: 'FmMailMessage')] as Object[],
                          "Another user has updated this FmMailMessage while you were editing")
                render(view: "edit", model: [fmMailMessageInstance: fmMailMessageInstance])
                return
            }
        }

        fmMailMessageInstance.properties = params

        if (!fmMailMessageInstance.save(flush: true)) {
            render(view: "edit", model: [fmMailMessageInstance: fmMailMessageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), fmMailMessageInstance.id])
        redirect(action: "show", id: fmMailMessageInstance.id)
    }

    def delete(Long id) {
        def fmMailMessageInstance = FmMailMessage.get(id)
        if (!fmMailMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), id])
            redirect(action: "list")
            return
        }

        try {
            fmMailMessageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fmMailMessage.label', default: 'FmMailMessage'), id])
            redirect(action: "show", id: id)
        }
    }
}
