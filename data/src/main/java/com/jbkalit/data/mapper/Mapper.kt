package com.jbkalit.data.mapper

interface Mapper<Response, Model> {
    fun map(response: Response): Model
}
