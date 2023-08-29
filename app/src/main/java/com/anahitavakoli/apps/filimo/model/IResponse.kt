package com.anahitavakoli.apps.filimo.model

interface IResponse<T> {

    fun onSuccess(response: T)
    fun onFailure(errorResponse: String)

}