node {
    git url: "https://github.com/vfarcic/${serviceName}.git"
    def flow = load "/data/scripts/workflow-util.groovy"
    flow.provision("rpi.yml")
    flow.buildTests(serviceName, registryIpPort)
    flow.runTests(serviceName, "tests", "")
    flow.buildService(serviceName, registryIpPort)
    flow.deploy(serviceName, prodIp)
    flow.updateProxy(serviceName, "prod")
    flow.runTests(serviceName, "integ", "-e DOMAIN=http://${proxyIp}")
}
