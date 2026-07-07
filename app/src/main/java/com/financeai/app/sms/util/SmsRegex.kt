package com.financeai.app.sms.util

/**
 * Centralized regular expressions used by the SMS parsing engine.
 *
 * All parsers should reuse these patterns instead of creating their own.
 */
object SmsRegex {

    /**
     * Matches currency amounts.
     *
     * Examples:
     * Rs.500
     * INR 1,250.50
     * ₹999
     */
    val AMOUNT = Regex(
        """(?:₹|Rs\.?|INR)\s?([0-9,]+(?:\.[0-9]{1,2})?)""",
        RegexOption.IGNORE_CASE
    )

    /**
     * Matches account numbers.
     *
     * Examples:
     * A/c XX1234
     * Account 5678
     */
    val ACCOUNT = Regex(
        """(?:A\/?c|Account)\s*(?:XX|X+)?(\d{3,})""",
        RegexOption.IGNORE_CASE
    )

    /**
     * Matches available balance.
     *
     * Examples:
     * Avl Bal ₹12,345.67
     * Available Balance Rs.1000
     */
    val BALANCE = Regex(
        """(?:Avl|Available)\s*Bal(?:ance)?[:\s]*(?:₹|Rs\.?|INR)?\s?([0-9,]+(?:\.[0-9]{1,2})?)""",
        RegexOption.IGNORE_CASE
    )

    /**
     * Matches UPI / transaction reference numbers.
     */
    val REFERENCE = Regex(
        """(?:Ref(?:erence)?|UPI Ref(?: No)?|Txn(?: ID)?)[\s:#-]*([A-Za-z0-9]+)""",
        RegexOption.IGNORE_CASE
    )

    /**
     * Matches masked account numbers.
     *
     * Examples:
     * XX1234
     * XXXX5678
     */
    val MASKED_ACCOUNT = Regex(
        """X+\d{3,}""",
        RegexOption.IGNORE_CASE
    )

    /**
     * Matches common debit keywords.
     */
    val DEBIT = Regex(
        """\b(debited|spent|paid|withdrawn|sent)\b""",
        RegexOption.IGNORE_CASE
    )

    /**
     * Matches common credit keywords.
     */
    val CREDIT = Regex(
        """\b(credited|received|deposit|refund)\b""",
        RegexOption.IGNORE_CASE
    )
}