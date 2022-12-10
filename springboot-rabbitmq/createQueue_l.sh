
# create Queue 
curl -i -u bk:12345 -H "content-type:application/json" \
-XPUT -d '{"auto_delete":false,"durable":true, "arguments":{}}'  \
          http://127.0.0.1:15672/api/queues/%2f/message.queue


#Create Exchange

curl -i -u bk:12345 -H "content-type:application/json" \
       -d '{"type":"topic","auto_delete":false,"durable":true,"internal":false,"arguments":{}}' \
      -XPUT http://127.0.0.1:15672/api/exchanges/%2F/message.exchange


#Create key Binding to exchange

curl -i -u bk:12345 -H "content-type:application/json" \
    -d '{"routing_key":"message.key"}' \
    -XPOST http://127.0.0.1:15672/api/bindings/%2F/e/message.exchange/q/message.queue
