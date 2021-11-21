package edu.eci.services

import edu.eci.models.Enterprise

interface EnterpriseService {

    fun createEnterprise(enterprise: Enterprise): Enterprise
    fun getCreatedEnterprises(): List<Enterprise>
}