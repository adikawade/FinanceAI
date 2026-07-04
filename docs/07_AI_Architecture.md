# FinanceAI — AI Architecture

**Document Version:** 1.0.0

## AI Philosophy
FinanceAI uses a provider abstraction layer so AI vendors can be replaced without changing business logic.

## Provider Interface
- AIProviderInterface
- Gemini (Primary)
- Future OpenAI Support
- Future Anthropic Support

## AI Responsibilities
- Transaction categorization
- Merchant learning
- Budget insights
- Tax assistance
- Investment education
- Receipt OCR processing

## Guardrails
- Never fabricate financial data.
- Clearly distinguish estimates from facts.
- Protect user privacy.
- Keep user-specific learning isolated.
