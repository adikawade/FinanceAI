# FinanceAI — Trip Expense & Settlement Engine

**Document Version:** 1.0.0

## Overview
The Trip Engine provides Splitwise-style shared expense management tightly integrated with transaction detection.

## Features
- Trip creation
- Member management
- Automatic expense detection
- Equal, percentage, exact and custom splits
- Debt minimization
- Settlement tracking

## Settlement Algorithm
1. Calculate net balance.
2. Separate debtors and creditors.
3. Minimize transfers using a greedy settlement algorithm.

## Design Goals
- Real-time synchronization
- Offline support
- Enterprise scalability
