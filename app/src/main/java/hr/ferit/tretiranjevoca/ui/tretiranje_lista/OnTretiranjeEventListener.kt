package hr.ferit.tretiranjevoca.ui.tretiranje_lista

import hr.ferit.tretiranjevoca.model.Tretiranje

interface OnTaskEventListener{
    fun onTaskSelected(id: Long?)
    fun onTaskLongPress(tretiranje: Tretiranje?): Boolean
}