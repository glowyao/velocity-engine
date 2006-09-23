#!/bin/sh

# Copyright 2000-2006 The Apache Software Foundation.
#
# Licensed under the Apache License, Version 2.0 (the "License")
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

echo "DBContextTest : please ensure MySQL is set up and jdbc drivers are in classpath. See DBContextTest.java for clues"
echo "This is an unsupported demo."

java -cp .:../bin/velocity-0.71.jar:$CLASSPATH DBContextTest dbtest.vm
