# enforcer-rules

[![Build Status](https://travis-ci.com/automatictester/enforcer-rules.svg?branch=master)](https://travis-ci.com/automatictester/enforcer-rules)
[![Central status](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/enforcer-rules/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/enforcer-rules)

Custom rules for Maven Enforcer Plugin.

## requireVersionPattern rule

This rule checks that the current project version matches defined regular expression pattern. Use standard Java regex syntax.  

### Usage example

Add Maven Enforcer Plugin with custom rule to your **pom.xml**: 

```xml
   <build>
      <plugins>
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-enforcer-plugin</artifactId>
            <version><!-- check maven central for most recent released version of maven-enforcer-plugin --></version>
            <dependencies>
               <dependency>
                  <groupId>uk.co.automatictester</groupId>
                  <artifactId>enforcer-rules</artifactId>
                  <version><!-- check maven central badge above for most recent released version of enforcer-rules --></version>
               </dependency>
            </dependencies>
            <executions>
               <execution>
                  <id>enforce</id>
                  <goals>
                     <goal>enforce</goal>
                  </goals>
                  <configuration>
                     <rules>
                        <requireVersionPattern implementation="uk.co.automatictester.enforcer.rules.RequireVersionPattern">
                           <versionPattern>^\d+\.\d+\.\d+$</versionPattern>
                        </requireVersionPattern>
                     </rules>
                  </configuration>
               </execution>
            </executions>
         </plugin>
      </plugins>
   </build>
```

In the above example, validation will pass e.g. for **1.2.3** and fail e.g. for **1.2-SNAPSHOT**. See 
[unit tests](https://github.com/automatictester/enforcer-rules/blob/master/src/test/java/uk/co/automatictester/enforcer/rules/ProjectVersionComparerTest.java) 
for more examples of regex patterns and passing/failing input.
  