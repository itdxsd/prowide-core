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

package com.prowidesoftware.swift.model.mt.mt5xx;

import com.prowidesoftware.swift.model.SwiftTagListBlock;
import com.prowidesoftware.swift.model.field.Field22H;
import com.prowidesoftware.swift.model.field.Field95C;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MT537Test {

    /**
     * Sequence A1, B2a and C1 use the same delimiter so we need to add parent sequence for unique find
     */
    @Test
    public void test1() {
        MT537 m = new MT537();
        m.append(MT537.SequenceA1.newInstance());
        m.append(MT537.SequenceB2.newInstance());
        m.append(MT537.SequenceC.newInstance());
        assertTrue(m.getSequenceB2aList().isEmpty());
        assertTrue(m.getSequenceC1List().isEmpty());
    }

    /**
     * Sequence A1, B2a and C1 use the same delimiter so we need to add parent sequence for unique find
     */
    @Test
    public void test2() {
        MT537 m = new MT537();
        m.append(MT537.SequenceA.newInstance());
        m.append(MT537.SequenceB2a.newInstance());
        m.append(MT537.SequenceC.newInstance());
        assertTrue(m.getSequenceA1List().isEmpty());
        assertTrue(m.getSequenceC1List().isEmpty());
    }

    /**
     * Sequence A1, B2a and C1 use the same delimiter so we need to add parent sequence for unique find
     */
    @Test
    public void test3() {
        MT537 m = new MT537();
        m.append(MT537.SequenceA.newInstance());
        m.append(MT537.SequenceB2.newInstance());
        m.append(MT537.SequenceC1.newInstance());
        assertTrue(m.getSequenceA1List().isEmpty());
        assertTrue(m.getSequenceB2aList().isEmpty());
    }

    @Test
    public void testC2a() {
        MT537 m = new MT537();
        m.append(MT537.SequenceC.newInstance(MT537.SequenceC2.newInstance(MT537.SequenceC2a.newInstance())));
        assertEquals(1, MT537.getSequenceCList(m.getSwiftMessage().getBlock4()).size());
        assertEquals(1, MT537.getSequenceC2List(m.getSwiftMessage().getBlock4()).size());
        assertEquals(1, MT537.getSequenceC2aList(m.getSwiftMessage().getBlock4()).size());

    }

    @Test
    public void testC2a_from_S285() {
        SwiftTagListBlock C2_contents = new SwiftTagListBlock()
                .append(Field22H.tag(":REDE//DELI"))
                .append(MT537.SequenceC2a.newInstance(Field95C.tag(":DEAG")));
        MT537 m = new MT537()
                .append(MT537.SequenceC.newInstance(
                        MT537.SequenceC2.newInstance(C2_contents)
                ));
        assertEquals(1, MT537.getSequenceCList(m.getSwiftMessage().getBlock4()).size());
        assertEquals(1, MT537.getSequenceC2List(m.getSwiftMessage().getBlock4()).size());
        assertEquals(1, MT537.getSequenceC2aList(m.getSwiftMessage().getBlock4()).size());

        assertEquals(1, m.getSequenceCList().size());
        assertEquals(1, m.getSequenceC2List().size());
        assertEquals(1, m.getSequenceC2aList().size());
    }

    /**
     * B2 and D1a1B1 have the same delimiter
     */
    @Test
    public void testB2() {
        MT537 m = new MT537();
        m.append(MT537.SequenceB.newInstance(MT537.SequenceB2.newInstance()));
        assertEquals(1, m.getSequenceB2List().size());
        assertTrue(m.getSequenceD1a1B1List().isEmpty());
    }

    /**
     * B2 and D1a1B1 have the same delimiter
     */
    @Test
    public void testD1a1B1() {
        MT537 m = new MT537();
        m.append(MT537.SequenceD1a1B.newInstance(MT537.SequenceD1a1B1.newInstance()));
        assertTrue(m.getSequenceB2List().isEmpty());
        assertEquals(1, m.getSequenceD1a1B1List().size());
    }

}