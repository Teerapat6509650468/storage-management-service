workspace {

    model {
        softwareSystem = softwaresystem "Storage Management Service" {
            service1 = group "Service 1" {
                service1Api = container "Goods Check-In" {
                    tags "Service 1" "Service API"
                }
                container "Goods Check-In Database" {
                    tags "Service 1" "Database"
                    service1Api -> this "Reads from and writes to"
                }
            }
            service2 = group "Service 2" {
                service2Api = container "Goods Retrieval" {
                    tags "Service 2" "Service API"
                }
                container "Retrieval Database" {
                    tags "Service 2" "Database"
                    service2Api -> this "Reads from and writes to"
                }
            }
            service3 = group "Service 3" {
                service3Api = container "Storage Management" {
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
            service1Api -> kafkacontainer "send message"
            service2Api -> kafkacontainer "send message"
            kafkacontainer -> service3Api "recieve message"
        }
    }
    views {
        systemContext softwareSystem {
            include *
            autolayout lr
        }

        container softwareSystem {
            include *
            autolayout lr
        }

        theme default
        
        styles {
                element "Database" {
                shape cylinder
                background #f5da81
                color #000000
                icon "cylinder"
            }
        }
    }
}
