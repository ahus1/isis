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
package org.apache.isis.metamodel.facets.object.domainobjectlayout;


import java.util.Optional;

import org.apache.isis.applib.annotation.ViewModelLayout;
import org.apache.isis.core.commons.internal.base._Strings;
import org.apache.isis.metamodel.facetapi.FacetHolder;
import org.apache.isis.metamodel.facets.all.describedas.DescribedAsFacet;
import org.apache.isis.metamodel.facets.all.describedas.DescribedAsFacetAbstract;


public class DescribedAsFacetForViewModelLayoutAnnotation extends DescribedAsFacetAbstract {

    public static DescribedAsFacet create(
            final Optional<ViewModelLayout> viewModelLayoutIfAny,
            final FacetHolder holder) {

        return viewModelLayoutIfAny
                .map(ViewModelLayout::describedAs)
                .filter(_Strings::isNotEmpty)
                .map(describedAs -> new DescribedAsFacetForViewModelLayoutAnnotation(describedAs, holder))
                .orElse(null);
    }

    private DescribedAsFacetForViewModelLayoutAnnotation(final String value, final FacetHolder holder) {
        super(value, holder);
    }
}
