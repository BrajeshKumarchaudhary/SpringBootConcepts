Get IP Address of Container
sudo docker inspect -f "{{ .NetworkSettings.IPAddress }}" Container_Name

Ping the IP address to make sure it’s available:

ping –c 3 172.17.0.2

ssh root@172.17.0.2
