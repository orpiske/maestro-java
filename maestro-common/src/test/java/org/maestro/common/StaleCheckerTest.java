/*
 * Copyright 2018 Otavio Rodolfo Piske
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.maestro.common;

import org.junit.Test;
import org.junit.Assert;

public class StaleCheckerTest {

    @Test
    public void testStale() {
        StaleChecker staleChecker = new NonProgressingStaleChecker(10);

        for (int i = 0; i < 10; i++) {
            Assert.assertFalse(staleChecker.isStale(i));
        }

        for (int i = 0; i < 9; i++) {
            Assert.assertFalse(staleChecker.isStale(10));
        }

        Assert.assertTrue(staleChecker.isStale(10));
    }
}
