package edu.eci.services

import edu.eci.models.Enterprise
import edu.eci.repositories.EnterpriseRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class EnterpriseServiceImpl(
    private val enterpriseRepository: EnterpriseRepository,
    private val kafkaPublisherService: KafkaPublisherService
): EnterpriseService {

    override fun createEnterprise(enterprise: Enterprise): Enterprise {

        this.enterpriseRepository.findByName(enterprise.name).ifPresent {
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "enterprise.already.exists")
        }

        if (enterprise.blackList || enterprise.reported){
            throw HttpStatusException(HttpStatus.BAD_REQUEST, "enterprise.bad.state")
        }

        enterprise.createdAt = LocalDateTime.now()

        return this.enterpriseRepository.save(enterprise).let { enterpriseDB ->
            this.kafkaPublisherService.sendOrchestratorData(enterpriseDB)
            enterpriseDB
        }
    }

    override fun getCreatedEnterprises(): List<Enterprise> {

        return this.enterpriseRepository.findAll().toList().sortedBy { it.createdAt }
    }
}