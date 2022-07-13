配置	                                           默认值	                    描述
spring.cloud.nacos.discovery.server-addr	 	                            注册中心服务器地址
spring.cloud.nacos.discovery.service	                                    ${spring.application.name}	命名当前服务名称
spring.cloud.nacos.discovery.weight	             1	                        权重, 1到100,值越大,权重越大
spring.cloud.nacos.discovery.ip	 	                                        注册ip , 当前服务注册到nacos的IP,最高优先级
spring.cloud.nacos.discovery.port	             -1	                        注册端口 , 当前服务注册到nacos的端口,默认情况下将自动检测,不需要配置
spring.cloud.nacos.discovery.namespace	 	                                命名空间
spring.cloud.nacos.discovery.access-key	 	                                阿里云账户访问秘钥
spring.cloud.nacos.discovery.secret-key	 	                                阿里云账户秘钥
spring.cloud,nacos.discovery.metadata	 	                                元数据  , 可以使用key-value格式定义一些元数据
spring.cloud.nacos.discovery.cluster-name	     Default	                集群名称
ribbon.nacos.enabled	                         true	                    是否集成ribbon
spring.cloud.nacos.config.server.addr	 	                                配置中心地址
spring.cloud.nacos.config.name	 	                                        首先使用配置的前缀,然后再使用名称,最后使用spring.application.name
spring.cloud.nacos.config.prefix	 	                                    首先使用配置的前缀,然后再使用名称,最后使用spring.application.name
spring.cloud.nacos.config.group	DEFAULT_GROUP	                            nacos的组配置
spring.cloud.nacos.config.file-extension	      properties	            data id的后缀
spring.cloud.nacos.config.timeout	              3000	                    从nacos获取配置超时时间
spring.cloud.nacos.config.namespace	 	                                    命名空间
spring.cloud.nacos.config.contentPath	 	                                Nacos server的上下文路径
spring.cloud.nacos.config.sharedDataids	 	                                共享配置的dataid , 共享配置的数据标记,用","分隔
spring.cloud.nacos.config.refreshableDataids	 	                        共享配置的动态刷新dataid , 共享配置的动态刷新数据标记, 用","分隔