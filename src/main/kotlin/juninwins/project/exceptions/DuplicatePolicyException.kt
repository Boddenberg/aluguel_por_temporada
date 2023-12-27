package juninwins.project.exceptions

class DuplicatePolicyException(policyType: String): RuntimeException("This policy : $policyType, has already been added to this accommodation") {
}