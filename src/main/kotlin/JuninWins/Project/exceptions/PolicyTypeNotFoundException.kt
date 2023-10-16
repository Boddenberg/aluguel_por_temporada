package JuninWins.Project.exceptions

import JuninWins.Project.enums.DiscountPolicyTypeEnum

class PolicyTypeNotFoundException(policyType: String, values: Array<DiscountPolicyTypeEnum>): RuntimeException("This policy type: [$policyType] not exists, please use values ${values.toList()}")