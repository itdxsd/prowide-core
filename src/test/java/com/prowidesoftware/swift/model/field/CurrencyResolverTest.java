/*
 * Copyright 2006-2021 Prowide
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.prowidesoftware.swift.model.field;

import com.prowidesoftware.swift.utils.SwiftFormatUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CurrencyResolverTest {

    @Test
    public void testResolve32Q_multiple() {

        Field32Q field = new Field32Q("USD/EUR");

        // list of currencies
        final List<Currency> currencies = field.currencies();
        assertNotNull(currencies);
        assertEquals(2, currencies.size());
        assertEquals(SwiftFormatUtils.getCurrency("USD"), currencies.get(0));
        assertEquals(SwiftFormatUtils.getCurrency("EUR"), currencies.get(1));

        // list of currency strings
        final List<String> currencyStrings = field.currencyStrings();
        assertNotNull(currencyStrings);
        assertEquals(2, currencyStrings.size());
        assertEquals("USD", currencyStrings.get(0));
        assertEquals("EUR", currencyStrings.get(1));

        // first currency
        assertEquals(SwiftFormatUtils.getCurrency("USD"), field.currency());

        // first currency string
        assertEquals("USD", field.currencyString());

        // initialize currencies
        field.initializeCurrencies(SwiftFormatUtils.getCurrency("GBP"));
        assertEquals(SwiftFormatUtils.getCurrency("GBP"), field.getComponent1AsCurrency());
        assertEquals(SwiftFormatUtils.getCurrency("GBP"), field.getComponent2AsCurrency());

        // initialize currency string
        field.initializeCurrencies("CHF");
        assertEquals("CHF", field.getCurrency1());
        assertEquals("CHF", field.getCurrency2());
    }

    @Test
    public void testResolve32M_single() {

        Field32M field = new Field32M("USD123,45");

        // list of currencies
        final List<Currency> currencies = field.currencies();
        assertNotNull(currencies);
        assertEquals(1, currencies.size());
        assertEquals(SwiftFormatUtils.getCurrency("USD"), currencies.get(0));

        // list of currency strings
        final List<String> currencyStrings = field.currencyStrings();
        assertNotNull(currencyStrings);
        assertEquals(1, currencyStrings.size());
        assertEquals("USD", currencyStrings.get(0));

        // first currency
        assertEquals(SwiftFormatUtils.getCurrency("USD"), field.currency());

        // first currency string
        assertEquals("USD", field.currencyString());

        // initialize currencies
        field.initializeCurrencies(SwiftFormatUtils.getCurrency("GBP"));
        assertEquals(SwiftFormatUtils.getCurrency("GBP"), field.getComponent1AsCurrency());

        // initialize currency string
        field.initializeCurrencies("CHF");
        assertEquals("CHF", field.getComponent1());
    }

    @Test
    public void testResolveComponentsPattern() {
        List<String> components = new ArrayList<>();
        components.add("c1");
        components.add("c2");
        components.add("c3");
        components.add("c4");
        components.add("c5");

        List<String> o = CurrencyResolver.resolveComponentsPattern("SC", components);
        assertEquals(1, o.size());
        assertEquals("c2", o.get(0));

        o = CurrencyResolver.resolveComponentsPattern("CS", components);
        assertEquals(1, o.size());
        assertEquals("c1", o.get(0));

        o = CurrencyResolver.resolveComponentsPattern("SCS", components);
        assertEquals(1, o.size());
        assertEquals("c2", o.get(0));

    }

    // FIXME fallo por un refactor que dejo mal el dummy container
    @Test
    public void testResolveCurrency() {
        List<String> list = new ArrayList<>();
        list.add("USD");
        DummyCurrencyContainer o = new DummyCurrencyContainer(list);
        assertEquals("USD", CurrencyResolver.resolveCurrencyString(o));
    }

    private static final class DummyCurrencyContainer implements CurrencyContainer {
        private final List<String> currencies;

        DummyCurrencyContainer(List<String> list) {
            this.currencies = list;
        }

        public String componentsPattern() {
            // TODO Auto-generated method stub
            return null;
        }

        public String typesPattern() {
            // TODO Auto-generated method stub
            return null;
        }

        public String parserPattern() {
            // TODO Auto-generated method stub
            return null;
        }

        public List<String> currencyStrings() {
            return this.currencies;
        }

        public String currencyString() {
            // TODO Auto-generated method stub
            return null;
        }

        public List<Currency> currencies() {
            // TODO Auto-generated method stub
            return null;
        }

        public Currency currency() {
            // TODO Auto-generated method stub
            return null;
        }

        public void initializeCurrencies(String cur) {
            // TODO Auto-generated method stub

        }

        public void initializeCurrencies(Currency cur) {
            // TODO Auto-generated method stub

        }

    }
}
