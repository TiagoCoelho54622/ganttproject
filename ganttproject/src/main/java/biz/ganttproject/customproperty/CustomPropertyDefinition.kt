/*
Copyright 2003-2012 Dmitry Barashev, GanttProject Team

This file is part of GanttProject, an opensource project management tool.

GanttProject is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

GanttProject is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with GanttProject.  If not, see <http://www.gnu.org/licenses/>.
 */
package biz.ganttproject.customproperty

import biz.ganttproject.core.option.DefaultStringOption
import biz.ganttproject.core.option.StringOption

interface CustomPropertyDefinition {
    val propertyClass: CustomPropertyClass
    fun setPropertyClass(propertyClass: CustomPropertyClass)
    val type: Class<*>
    val typeAsString: String
    val id: String
    val defaultValue: Any?
    var name: String
    var defaultValueAsString: String?
    val attributes: Map<String, String>
    var calculationMethod: CalculationMethod?
}

interface CalculationMethod {
  fun buildQuery(): String
  fun queryParts(): List<StringOption>
}

class CalculateFromSingleRow(expressionString: String) : CalculationMethod {
  val expression = DefaultStringOption("customPropertyCalculation.fromRow").also {
    it.value = expressionString
  }
  override fun buildQuery(): String = expression.value
  override fun queryParts(): List<StringOption> = listOf(expression)
}