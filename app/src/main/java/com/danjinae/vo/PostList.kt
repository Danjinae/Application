package com.danjinae.vo

data class PostList (
    var content: List<PostModel>,
    var pageable: Pageable,
    var last: Boolean,
    var totalPages: Int,
    var totalElements: Int,
    var size: Int,
    var number: Int,
    var sort: Sort,
    var first: Boolean,
    var numberOfElements: Int,
    var empty: Boolean
    ){

}
