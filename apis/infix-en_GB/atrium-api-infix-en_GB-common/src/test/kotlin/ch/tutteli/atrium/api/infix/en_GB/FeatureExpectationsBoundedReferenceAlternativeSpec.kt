package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.workaround.it
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.TestData
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class FeatureExpectationsBoundedReferenceAlternativeSpec : ch.tutteli.atrium.specs.integration.FeatureExpectationsSpec(
    propertyImmediate,
    propertyLazy,
    f0Immediate,
    f1Immediate,
    f2Immediate,
    f3Immediate,
    f4Immediate,
    f5Immediate,
    f0Lazy,
    f1Lazy,
    f2Lazy,
    f3Lazy,
    f4Lazy,
    f5Lazy,

    propertyNullableDoesNotHold,
    f0NullableDoesNotHold,
    f1NullableDoesNotHold,
    f2NullableDoesNotHold,
    f3NullableDoesNotHold,
    f4NullableDoesNotHold,
    f5NullableDoesNotHold,

    propertyNullableHolds,
    f0NullableHolds,
    f1NullableHolds,
    f2NullableHolds,
    f3NullableHolds,
    f4NullableHolds,
    f5NullableHolds,

    propertyLazyWithNestedImmediate,
    propertyLazyWithNestedLazy,

    propertyEmptyAssertionCreator,
    f0EmptyAssertionCreator,
    f1EmptyAssertionCreator,
    f2EmptyAssertionCreator,
    f3EmptyAssertionCreator,
    f4EmptyAssertionCreator,
    f5EmptyAssertionCreator,

    isAbleToEvaluateDescription = false
) {

    //TODO remove type parameters for `of` with Kotiln 1.4 including parentheses (make the calls infix again
    companion object : WithAsciiReporter() {
        //@formatter:off
        val propertyImmediate: F = { it feature { p(it::nonNullValue) } contains "hello" }
        val propertyLazy: F = { it feature of<TestData, String>({ p(it::nonNullValue) }) { it contains "hello" } }
        val f0Immediate: F = { it feature { f0(it::return0) } contains "hello" }
        val f1Immediate: F = { it feature { f1(it::return1, "a") } contains "hello" }
        val f2Immediate: F = { it feature { f2(it::return2, "a", 1) } contains "hello" }
        val f3Immediate: F = { it feature { f3(it::return3, "a", 1, true) } contains "hello" }
        val f4Immediate: F = { it feature { f4(it::return4, "a", 1, true, 1.2) } contains "hello" }
        val f5Immediate: F = { it feature { f5(it::return5, "a", 1, true, 1.2, 'b') } contains "hello" }
        val f0Lazy: F = { it feature of<TestData, String>({ f0(it::return0) }) {  it contains "hello" } }
        val f1Lazy: F = { it feature of<TestData, String>({ f1(it::return1, "a") }) {  it contains "hello" } }
        val f2Lazy: F = { it feature of<TestData, String>({ f2(it::return2, "a", 1) }) {  it contains "hello" } }
        val f3Lazy: F = { it feature of<TestData, String>({ f3(it::return3, "a", 1, true) }) {  it contains "hello" } }
        val f4Lazy: F = { it feature of<TestData, String>({ f4(it::return4, "a", 1, true, 1.2) }) {  it contains "hello" } }
        val f5Lazy: F = { it feature of<TestData, String>({ f5(it::return5, "a", 1, true, 1.2, 'b') }) {  it contains "hello" } }

        val propertyNullableDoesNotHold: F = { it feature { p(it::nullableValue) } toBe null }
        val f0NullableDoesNotHold: F = { it feature { f0(it::returnNullable0) } toBe null }
        val f1NullableDoesNotHold: F = { it feature { f1(it::returnNullable1, "a") } toBe null }
        val f2NullableDoesNotHold: F = { it feature { f2(it::returnNullable2, "a", 1) } toBe null }
        val f3NullableDoesNotHold: F = { it feature { f3(it::returnNullable3, "a", 1, true) } toBe null }
        val f4NullableDoesNotHold: F = { it feature { f4(it::returnNullable4, "a", 1, true, 1.2) } toBe null }
        val f5NullableDoesNotHold: F = { it feature { f5(it::returnNullable5, "a", 1, true, 1.2, 'b') } toBe null }

        val propertyNullableHolds: F = { it feature { p(it::nullableValue) } notToBeNull { it toBe 1 } }
        val f0NullableHolds: F = { it feature { f0(it::returnNullable0) } notToBeNull { it toBe 1 } }
        val f1NullableHolds: F = { it feature { f1(it::returnNullable1, "a") } notToBeNull { it toBe 1 } }
        val f2NullableHolds: F = { it feature { f2(it::returnNullable2, "a", 1) } notToBeNull { it toBe 1 } }
        val f3NullableHolds: F = { it feature { f3(it::returnNullable3, "a", 1, true) } notToBeNull { it toBe 1 } }
        val f4NullableHolds: F = { it feature { f4(it::returnNullable4, "a", 1, true, 1.2) } notToBeNull { it toBe 1 } }
        val f5NullableHolds: F = { it feature { f5(it::returnNullable5, "a", 1, true, 1.2, 'b') } notToBeNull { it toBe 1 } }
        //@formatter:on

        val propertyLazyWithNestedImmediate: F = {
            it feature { p(it::nonNullValue) } it {
                it feature { p(it::length) } toBe 12
            }
        }
        val propertyLazyWithNestedLazy: F = {
            it feature { p(it::nonNullValue) } it {
                it feature { p(it::length) } it { it toBe 12 }
            }
        }

        val propertyEmptyAssertionCreator: F = { it feature of<TestData, String>({ p(it::nonNullValue) }) {} }
        val f0EmptyAssertionCreator: F = { it feature of<TestData, String>({ f0(it::return0) }) {} }
        val f1EmptyAssertionCreator: F = { it feature of<TestData, String>({ f1(it::return1, "a") }) {} }
        val f2EmptyAssertionCreator: F = { it feature of<TestData, String>({ f2(it::return2, "a", 1) }) {} }
        val f3EmptyAssertionCreator: F = { it feature of<TestData, String>({ f3(it::return3, "a", 1, true) }) {} }
        val f4EmptyAssertionCreator: F = { it feature of<TestData, String>({ f4(it::return4, "a", 1, true, 1.2) }) {} }
        val f5EmptyAssertionCreator: F =
            { it feature (of<TestData, String>({ f5(it::return5, "a", 1, true, 1.2, 'b') }) {}) }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1 feature { p(it::size) }
        a1 feature { p(it::size) } it {}

        a1b feature { p(it::size) }
        a1b feature { p(it::size) } it {}

        star feature { p(it::size) }
        star feature { p(it::size) } it {}
    }
}
