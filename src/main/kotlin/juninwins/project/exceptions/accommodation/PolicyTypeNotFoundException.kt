package juninwins.project.exceptions.accommodation

import juninwins.project.enums.DiscountPolicyTypeEnum

class PolicyTypeNotFoundException(policyType: String, values: Array<DiscountPolicyTypeEnum>): RuntimeException("This policy type: [$policyType] not exists, please use values ${values.toList()}")