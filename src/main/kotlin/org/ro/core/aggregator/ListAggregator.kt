package org.ro.core.aggregator

import kotlinx.serialization.Serializable
import org.ro.core.UiManager
import org.ro.core.event.LogEntry
import org.ro.core.model.DisplayList
import org.ro.layout.Layout
import org.ro.to.Link
import org.ro.to.Property
import org.ro.to.ResultList
import org.ro.to.TObject

/** sequence of operations:
 * (0) list
 * (1) FR_OBJECT                TObjectHandler -> invoke()
 * (2) FR_OBJECT_LAYOUT         layoutHandler -> invoke(layout.getProperties()[].getLink()) link can be null?
 * (3) FR_OBJECT_PROPERTY       PropertyHandler -> invoke()
 * (4) FR_PROPERTY_DESCRIPTION  PropertyDescriptionHandler
 */
@Serializable
class ListAggregator(private val actionTitle: String) : BaseAggregator() {
    var list: DisplayList

    init {
        list = DisplayList(actionTitle)
    }

    override fun update(logEntry: LogEntry) {
        val obj = logEntry.getObj()

        when (obj) {
            is ResultList -> handleList(obj)
            is TObject -> handleObject(obj)
            is Layout -> handleLayout(obj)
            is Property -> handleProperty(obj)
            else -> log(logEntry)
        }

        if (list.canBeDisplayed()) {
            UiManager.handleView(list)
        }
    }

    private fun handleList(resultList: ResultList) {
        val result = resultList.result!!
        val members = result.value
        for (l: Link in members) {
            invoke(l)
        }
    }

    private fun handleObject(obj: TObject) {
        list.addData(obj)
        val link = obj.getLayoutLink()
        if (link != null) {
            invoke(link)
        }
    }

    private fun handleLayout(layout: Layout) {
        list.layout = layout
        val pls = layout.properties
        for (pl in pls) {
            val l = pl.link!!
            invoke(l)
        }
    }

    private fun handleProperty(p: Property) {
        if (p.isPropertyDescription()) {
            list.addPropertyLabel(p)
        } else {
            list.addProperty(p)
            val descLink = p.descriptionLink()!!
            invoke(descLink)
        }
    }

}
