package dynamiccurrencyrate

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"curRate")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
