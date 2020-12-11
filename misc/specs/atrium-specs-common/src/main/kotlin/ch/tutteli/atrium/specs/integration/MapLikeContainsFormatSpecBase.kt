package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import ch.tutteli.atrium.translations.DescriptionMapLikeAssertion
import org.spekframework.spek2.dsl.Root

abstract class MapLikeContainsFormatSpecBase(
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    spec: Root.() -> Unit
) : MapLikeContainsSpecBase(spec) {
    init {
        Companion.rootBulletPoint = rootBulletPoint
        Companion.successfulBulletPoint = successfulBulletPoint
        Companion.failingBulletPoint = failingBulletPoint
        Companion.warningBulletPoint = warningBulletPoint
        Companion.listBulletPoint = listBulletPoint
        Companion.explanatoryBulletPoint = explanatoryBulletPoint
        Companion.featureArrow = featureArrow
        Companion.featureBulletPoint = featureBulletPoint

        indentBulletPoint = " ".repeat(rootBulletPoint.length)
        indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
        indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
        indentListBulletPoint = " ".repeat(listBulletPoint.length)
        indentFeatureArrow = " ".repeat(featureArrow.length)
        indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length)

//        val explanatoryPointWithIndent =
//            "$indentFeatureArrow$indentFeatureBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
//
//        entryWhichWithFeature =
//            "$indentFeatureArrow$featureBulletPoint${IterableContainsEntriesSpecBase.anEntryWhich}"
//        anEntryAfterSuccess =
//            "$entryWhichWithFeature: ${IterableContainsSpecBase.separator}$indentBulletPoint$indentSuccessfulBulletPoint$explanatoryPointWithIndent"
//        anEntryAfterFailing =
//            "$entryWhichWithFeature: ${separator}$indentBulletPoint$indentFailingBulletPoint$explanatoryPointWithIndent"
    }

    companion object {
        // does not work if we would run the tests in parallel,
        // in case we want, then copy this to the corresponding specs
        var rootBulletPoint = ""
        var successfulBulletPoint = ""
        var failingBulletPoint = ""
        var warningBulletPoint = ""
        var listBulletPoint = ""
        var explanatoryBulletPoint = ""
        var featureArrow = ""
        var featureBulletPoint = ""
        var indentBulletPoint = " "
        var indentSuccessfulBulletPoint = ""
        var indentFailingBulletPoint = ""
        var indentListBulletPoint = ""
        var indentFeatureArrow = ""
        var indentFeatureBulletPoint = ""
//        var entryWhichWithFeature = ""
//        var anEntryAfterSuccess = ""
//        var anEntryAfterFailing = ""

        val sizeDescr = DescriptionMapAssertion.SIZE.getDefault()

        fun Expect<String>.containsSize(actual: Int, expected: Int) =
            contains.exactly(1)
                .regex("\\Q$rootBulletPoint$featureArrow$sizeDescr\\E: $actual[^:]+${DescriptionAnyAssertion.TO_BE.getDefault()}: $expected")


        fun Expect<String>.containsInAnyOrderOnlyDescr() =
            contains.exactly(1).value(
                "$rootBulletPoint${
                    DescriptionMapLikeAssertion.IN_ANY_ORDER_ONLY.getDefault()
                        .format(DescriptionMapLikeAssertion.CONTAINS.getDefault())
                }:"
            )


        fun Expect<String>.containsInOrderOnlyDescr() =
            contains.exactly(1).value(
                "$rootBulletPoint${
                    DescriptionMapLikeAssertion.IN_ORDER_ONLY.getDefault()
                        .format(DescriptionMapLikeAssertion.CONTAINS.getDefault())
                }:"
            )

        fun Expect<String>.entrySuccess(key: String, actual: Any, expected: String): Expect<String> {
            return this.contains.exactly(1).regex(
                "$indentBulletPoint\\Q$successfulBulletPoint$featureArrow${entry(key)}: $actual\\E.*${separator}" +
                    "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$expected"
            )
        }

        fun Expect<String>.entryFailing(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.contains.exactly(1).regex(
                "\\Q$failingBulletPoint$featureArrow${entry(key)}: $actual\\E.*${separator}" +
                    "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$expected"
            )
        }
        fun Expect<String>.entryFailingExplaining(key: String?, actual: Any?, expected: String): Expect<String> {
            return this.contains.exactly(1).regex(
                "\\Q$failingBulletPoint$featureArrow${entry(key)}: $actual\\E.*${separator}" +
                    "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.entryNonExisting(key: String, expected: String): Expect<String> {
            return this.contains.exactly(1).regex(
                "\\Q$failingBulletPoint$featureArrow${entry(key)}: $keyDoesNotExist\\E.*${separator}" +
                    "$indentBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$expected"
            )
        }

        fun Expect<String>.additionalEntries(vararg pairs: Pair<String, Any>): Expect<String> =
            and {
                val additionalEntries =
                    "\\Q$warningBulletPoint${DescriptionMapLikeAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()}\\E: $separator"
                contains.exactly(1).regex(additionalEntries)
                pairs.forEach { (key, value) ->
                    contains.exactly(1).regex(additionalEntries + ".*$listBulletPoint${entry(key, value)}")
                }
            }
    }
}
