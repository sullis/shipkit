package org.shipkit.internal.gradle.version

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class VersionInfoFactoryTest extends Specification {

    @Rule public TemporaryFolder tmp = new TemporaryFolder()

    def "provides version object"() {
        def versionFile = tmp.newFile()
        versionFile << "version=1.0.0"

        def missingFile = new File("does not exist")

        expect:
        new VersionInfoFactory().createVersionInfo(versionFile, "foo", ["foo", "build"]).version == "1.0.0"
        new VersionInfoFactory().createVersionInfo(versionFile, "foo", ["foo", "snapshot"]).version == "1.0.0-SNAPSHOT"

        new VersionInfoFactory().createVersionInfo(missingFile, "0.9", ["foo", "build"]).version == "0.9"
        new VersionInfoFactory().createVersionInfo(missingFile, "0.9", ["foo", "snapshot"]).version == "0.9-SNAPSHOT"
    }
}
