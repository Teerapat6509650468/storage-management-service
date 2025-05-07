workspace {

    model {
        softwareSystem = softwaresystem "Storage Management Service" {
            service1 = group "Goods Check-In" {
                service1Api = container "Goods Check-In API" {
                    tags "Service 1" "Service API"
                }
                container "Goods Check-In Database" {
                    tags "Service 1" "Database"
                    service1Api -> this "Reads from and writes to"
                }
            }
            service2 = group "Goods Retrieval" {
                service2Api = container "Goods Retrieval API" {
                    tags "Service 2" "Service API"
                }
                container "Retrieval Database" {
                    tags "Service 2" "Database"
                    service2Api -> this "Reads from and writes to"
                }
            }
            service3 = group "Storage Management" {
                service3Api = container "Storage Management API" {
                    tags "Service 3" "Service API"
                }
                container "Storage Management Database" {
                    tags "Service 3" "Database"
                    service3Api -> this "Reads from and writes to"
                }
            }
            kafka = group "Kafka" {
                kafkacontainer = container "Kafka Container" {
                    tag "Kafka"                    
                }
            }
            service1Api -> kafkacontainer "send message: product(s) check-in warehouse"
            service2Api -> kafkacontainer "send message: product(s) retrieve warehouse"
            kafkacontainer -> service3Api "recieve message: check-in/retrive from warehouse"
            service3Api -> kafkacontainer "send message: ACCEPT or REJECT check-in/retrive request(s)"
            kafkacontainer -> service1Api "return request: check-in SUCCESS/FAILURE"
            kafkacontainer -> service2Api "return request: retrieve SUCCESS/FAILURE" 
        }
    }
    views {
        systemContext softwareSystem {
            include *
            autolayout
        }

        container softwareSystem {
            include *
            autolayout
        }

        theme default
        
        styles {
                element "Database" {
                shape cylinder
                background #00a3ee
                icon "cylinder"
            }
                element "Service API" {
                shape hexagon
                background #6DB33F
            }
        }
    }
}
