# FinanceAI — Architecture Decision Log (ADR)

**Document Version:** 1.0.0

## ADR-001
Decision: Android application will be developed natively using Kotlin and Jetpack Compose.
Reason: Native Android APIs provide the most reliable background processing and SMS capabilities.

## ADR-002
Decision: iPhone client will be a Progressive Web App (PWA).
Reason: Enables cross-device access while integrating with Apple Shortcuts.

## ADR-003
Decision: Firebase will be the primary backend platform.
Reason: Provides authentication, Firestore, Cloud Functions, Storage, Remote Config and App Check in a unified ecosystem.

## ADR-004
Decision: AI providers will be abstracted behind a common interface.
Reason: Prevents vendor lock-in and allows future provider replacement.
