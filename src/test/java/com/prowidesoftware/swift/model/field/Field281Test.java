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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for Field281 and similar fields.
 *
 * @since 7.8.8
 */
public class Field281Test extends AbstractFieldTest {

    @Override
    @Test
    public void testSerialization() {
        testSerializationImpl("281",
                "1020MIR4567890123456789012345678AFOO"
        );
    }

    @Test
    public void testParse281() {
        Field281 f = new Field281("1020MIR4567890123456789012345678AFOO");
        assertNotNull(f, "Parse of field failed");
        assertEquals("1020", f.getComponent1());
        assertEquals("MIR4567890123456789012345678", f.getComponent2());
        assertEquals("A", f.getComponent3());
        assertEquals("FOO", f.getComponent4());
    }

}