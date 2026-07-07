package com.financeai.app.sms.filter

/**
 * Centralized keywords used to identify financial SMS.
 *
 * All filtering should use this object instead of hardcoded strings.
 */
object SmsKeywords {

    /**
     * Bank sender identifiers.
     */
    val BANK_SENDERS = setOf(

        "SBI",
        "SBIINB",
        "HDFCBK",
        "HDFC",
        "ICICIB",
        "ICICI",
        "AXISBK",
        "AXIS",
        "KOTAK",
        "PNB",
        "BOB",
        "CANBNK",
        "UNIONBK",
        "INDUSB",
        "YESBANK",
        "IDFCFB",
        "AUFB",
        "PAYTM",
        "AIRTEL",
        "JUPITER",
        "FI"
    )

    /**
     * Financial keywords.
     */
    val FINANCIAL_KEYWORDS = setOf(

        "credited",
        "debited",
        "withdrawn",
        "deposit",
        "payment",
        "paid",
        "received",
        "spent",
        "purchase",
        "txn",
        "transaction",
        "balance",
        "upi",
        "imps",
        "neft",
        "rtgs",
        "card",
        "atm"
    )

    /**
     * UPI apps.
     */
    val UPI_APPS = setOf(

        "PhonePe",
        "Google Pay",
        "GPay",
        "Paytm",
        "BHIM",
        "Amazon Pay",
        "CRED",
        "Mobikwik",
        "Freecharge"
    )

    /**
     * Promotional keywords.
     * If these dominate the SMS, it can usually be ignored.
     */
    val PROMOTIONAL_KEYWORDS = setOf(

        "offer",
        "cashback",
        "sale",
        "discount",
        "coupon",
        "win",
        "reward",
        "voucher",
        "shopping",
        "festival offer"
    )
}