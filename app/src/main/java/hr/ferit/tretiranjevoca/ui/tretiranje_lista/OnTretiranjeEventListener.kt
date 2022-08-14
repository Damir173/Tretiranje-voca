package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import hr.ferit.tretiranjevoca.model.Tretiranje

interface OnTretiranjeEventListener{
    fun onItemSelected(id: Long?)
    fun onItemPress(tretiranje: Tretiranje?): Boolean
}