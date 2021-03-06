/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.metamodel.facets.value.datetimejdk8offset;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.isis.commons.internal.collections._Maps;
import org.apache.isis.config.IsisConfiguration.Value.FormatIdentifier;
import org.apache.isis.metamodel.facetapi.FacetHolder;
import org.apache.isis.metamodel.facets.value.ValueSemanticsProviderAbstractTemporal;

public abstract class Jdk8OffsetDateTimeValueSemanticsProviderAbstract<T> extends ValueSemanticsProviderAbstractTemporal<T> {

    private static final Map<String, DateFormat> FORMATS = _Maps.newHashMap();

    static {
        FORMATS.put(ISO_ENCODING_FORMAT, createDateEncodingFormat("yyyyMMdd"));
        FORMATS.put("iso", createDateFormat("yyyy-MM-dd"));
        FORMATS.put("medium", DateFormat.getDateInstance(DateFormat.MEDIUM));
    }

    public Jdk8OffsetDateTimeValueSemanticsProviderAbstract(final FacetHolder holder, final Class<T> adaptedClass, final T defaultValue) {
        super(FormatIdentifier.DATE, holder, adaptedClass, 12, Immutability.IMMUTABLE, EqualByContent.HONOURED, defaultValue);

        String formatRequired = getConfiguration()
                .getValue().getFormatOrElse(FormatIdentifier.DATE, "medium");
        
        if (formatRequired == null) {
            format = formats().get(defaultFormat());
        } else {
            setMask(formatRequired);
        }
    }


    // //////////////////////////////////////////////////////////////////
    // temporal-specific stuff
    // //////////////////////////////////////////////////////////////////

    @Override
    protected void clearFields(final Calendar cal) {
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    @Override
    protected String defaultFormat() {
        return "medium";
    }

    @Override
    protected boolean ignoreTimeZone() {
        return true;
    }

    @Override
    protected Map<String, DateFormat> formats() {
        return FORMATS;
    }

    @Override
    public String toString() {
        return "DateValueSemanticsProvider: " + format;
    }

    @Override
    protected DateFormat format() {
        final Locale locale = Locale.getDefault();

        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        dateFormat.setTimeZone(UTC_TIME_ZONE);
        return dateFormat;
    }

    @Override
    protected List<DateFormat> formatsToTry() {

        final Locale locale = Locale.getDefault();

        List<DateFormat> formats = new ArrayList<>();

        formats.add(DateFormat.getDateInstance(DateFormat.LONG, locale));
        formats.add(DateFormat.getDateInstance(DateFormat.MEDIUM, locale));
        formats.add(DateFormat.getDateInstance(DateFormat.SHORT, locale));
        formats.add(createDateFormat("yyyy-MM-dd"));
        formats.add(createDateFormat("yyyyMMdd"));

        for (DateFormat format : formats) {
            format.setTimeZone(UTC_TIME_ZONE);
        }

        return formats;
    }

}
