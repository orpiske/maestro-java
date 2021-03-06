/*
 * Copyright 2018 Otavio R. Piske <angusyoung@gmail.com>
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

package org.maestro.common.client.notes;

public class TestExecutionInfoBuilder {
    private String description;
    private String comment;
    private String testName;
    private String scriptName;

    private int sutId = SutDetails.UNSPECIFIED;
    private String sutName;
    private String sutVersion;
    private String sutJvmVersion;
    private String sutOtherInfo;
    private String sutTags;
    private String labName;
    private String testTags;

    private static String setOrDefault(final String value) {
        if (value == null) {
            return "";
        }

        return value;
    }

    public TestExecutionInfoBuilder withDescription(String description) {
        this.description = setOrDefault(description);

        return this;
    }

    public TestExecutionInfoBuilder withComment(String comment) {
        this.comment = setOrDefault(comment);

        return this;
    }

    public TestExecutionInfoBuilder withTestName(String testName) {
        this.testName = setOrDefault(testName);

        return this;
    }

    public TestExecutionInfoBuilder withScriptName(String scriptName) {
        this.scriptName = setOrDefault(scriptName);

        return this;
    }

    public TestExecutionInfoBuilder withSutId(int sutId) {
        this.sutId = sutId;

        return this;
    }

    public TestExecutionInfoBuilder withSutId(String sutId) {
        if (sutId == null) {
            return withSutId(SutDetails.UNSPECIFIED);
        }

        return withSutId(Integer.parseInt(sutId));
    }

    public TestExecutionInfoBuilder withSutName(String sutName) {
        this.sutName = setOrDefault(sutName);

        return this;
    }

    public TestExecutionInfoBuilder withSutVersion(String sutVersion) {
        this.sutVersion = setOrDefault(sutVersion);

        return this;
    }



    public TestExecutionInfoBuilder withSutJvmVersion(String sutJvmVersion) {
        this.sutJvmVersion = setOrDefault(sutJvmVersion);

        return this;
    }

    public TestExecutionInfoBuilder withSutOtherInfo(String sutOtherInfo) {
        this.sutOtherInfo = setOrDefault(sutOtherInfo);

        return this;
    }

    public TestExecutionInfoBuilder withSutTags(String sutTags) {
        this.sutTags = setOrDefault(sutTags);

        return this;
    }

    public TestExecutionInfoBuilder withTestTags(String testTags) {
        this.testTags = setOrDefault(testTags);

        return this;
    }

    public TestExecutionInfoBuilder withLabName(String labName) {
        this.labName = setOrDefault(labName);

        return this;
    }

    public static TestExecutionInfoBuilder newBuilder() {
        return new TestExecutionInfoBuilder();
    }

    public TestExecutionInfo build() {
        final TestDetails testDetails = new TestDetails(description, comment);
        final Test test = new Test(Test.NEXT, Test.NEXT, testName, scriptName, testDetails);

        SutDetails sutDetails = null;

        if (sutId != SutDetails.UNSPECIFIED) {
            sutDetails = new SutDetails(sutId, labName, testTags);
        }
        else {
            if (sutName != null && !sutName.isEmpty() && sutVersion != null && !sutVersion.isEmpty() && sutTags != null && !sutTags.isEmpty()) {
                sutDetails = new SutDetails(this.sutName, this.sutVersion, this.sutJvmVersion, this.sutOtherInfo,
                        this.sutTags, this.labName, this.testTags);
            }
        }

        return new TestExecutionInfo(test, sutDetails);
    }
}
