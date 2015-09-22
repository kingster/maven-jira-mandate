# maven-jira-mandate

An [maven](https://maven.apache.org/) (Apache Maven) plugin for generating [Git Hooks](http://git-scm.com/docs/githooks) to force mandate JIRA-ID in commit's.
## Add Plugin

To add maven-jira-mandate functionality to your project add the following to your `pom.xml` file:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.flipkart.jira</groupId>
            <artifactId>maven-jira-mandate</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <configuration>
                <jira>BRO</jira>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>run</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
  
## Committing Changes

```bash
git commit -m "BRO-XX <commit message>"
git commit -m "BRO-XX: <commit message>"
```