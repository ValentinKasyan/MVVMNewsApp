package com.inter.mvvmnewsapp.main.models

data class Source(
    val id: String,
    val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Source
        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        val const = 31
        result = const * result + name.hashCode()
        if (!name.isNullOrEmpty()) {
            result = const * result + name.hashCode()
        }
        return result
    }

}