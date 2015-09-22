

package com.flipkart.jira;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.Properties;

@Mojo(name = "run", defaultPhase = LifecyclePhase.COMPILE)
public class JiraIdPlugin extends AbstractMojo {

    @Parameter(property = "run.jira")
    private String jira;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (jira == null)
            throw new MojoExecutionException("Jira Project Not Defined in Configuration");

        String contents = "test \"\" != \"$(grep -E '("+jira+")-\\d+( |:)\\w*' \"$1\")\" || {\n" +
                "echo >&2 'ERROR: Commit message is missing Jira Issue Number in Following Format: <project-ticketid:>, eg: "+jira+"-142:commit message'\n" +
                "exit 1\n" +
                "}";

        File commitMsgHook = new File (project.getBasedir().getPath() + "/.git/hooks/commit-msg" );

        getLog().debug("JiraIdPlugin commitMsgHook [" + commitMsgHook.getAbsolutePath() + "]");

        try {
            FileUtils.writeStringToFile(commitMsgHook, contents);
            commitMsgHook.setExecutable(true);
        }catch (Exception e){
            getLog().error(e);
            throw new MojoFailureException("Unable to Set JiraId Mandate ");
        }

        getLog().info("Commit Message Hook set to Project-Id [" + jira + "]");

    }

}