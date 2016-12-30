# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.define "dev" do |d|
    d.vm.box = "ubuntu/trusty64"
    d.vm.hostname = "dev"
    d.vm.network "private_network", ip: "10.100.198.200"
    d.vm.provision :shell, path: "scripts/bootstrap_ansible.sh"
    d.vm.provision :shell, inline: "PYTHONUNBUFFERED=1 ansible-playbook /vagrant/ansible/dev.yml -c local"
    d.vm.provider "virtualbox" do |v|
      v.memory = 2048
    end
  end
  config.vm.define "rpi" do |d|
    d.vm.box = "ubuntu/trusty64"
    d.vm.hostname = "rpi"
    d.vm.network "private_network", ip: "10.100.198.201"
    d.vm.provider "virtualbox" do |v|
      v.memory = 1024
    end
  end
  if Vagrant.has_plugin?("vagrant-cachier")
    config.cache.scope = :box
  end
  if Vagrant.has_plugin?("vagrant-vbguest")
    config.vbguest.auto_update = false
    config.vbguest.no_install = true
    config.vbguest.no_remote = true
  end
end
