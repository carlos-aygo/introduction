package edu.eci.controllers

import edu.eci.models.Enterprise
import edu.eci.services.EnterpriseService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.validation.Valid

@Controller("enterprises")
open class EnterpriseController(
    private val enterpriseService: EnterpriseService
) {

    @Post
    open fun createEnterprise(
        @Body @Valid enterprise: Enterprise
    ): Enterprise {

        return this.enterpriseService.createEnterprise(enterprise)
    }

    @Get
    open fun getEnabledEnterprises(): List<Enterprise> {

        return this.enterpriseService.getCreatedEnterprises()
    }
}