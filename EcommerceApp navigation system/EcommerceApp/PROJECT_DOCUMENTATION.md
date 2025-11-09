# E-Commerce Interface Development Project
## Electronic Store Application

### Project Information
- **Theme:** Electronics Store
- **Technology:** Java Swing
- **Pattern:** MVC (Model-View-Controller)
- **Sessions Completed:** 1-3

---

## 📋 Project Structure

```
EcommerceApp/
├── src/
│   ├── Main.java                          # Application entry point
│   └── com/electronicstore/
│       ├── models/                        # Data models
│       │   ├── Product.java              # Product entity
│       │   └── CartItem.java             # Shopping cart item
│       ├── controllers/                   # Business logic
│       │   ├── ProductController.java    # Product management
│       │   └── CartController.java       # Cart operations
│       └── ui/                           # User interface
│           ├── MainWindow.java           # Main application window
│           ├── ProductPage.java          # Product catalog page
│           ├── CartPage.java             # Shopping cart page
│           └── LoginPage.java            # Login/checkout page
├── run.bat                               # Windows batch runner
├── run.ps1                               # PowerShell runner
└── compile-and-run.bat                   # Quick compile & run
```

---

## 🎯 Sessions 1-3 Implementation

### **Session 1: Project Idea and Mock-up Design**
✅ **Completed**

**HCI Principles Applied:**
- **Clarity:** Clean, organized layout with clear visual hierarchy
- **Creativity:** Modern color scheme (blue primary, green success, red accent)
- **Visibility:** All navigation options clearly visible
- **Consistency:** Uniform button styles, fonts, and spacing throughout
- **Feedback:** Hover effects, visual state changes, confirmation dialogs

**Design Features:**
- Professional header with branding
- Intuitive navigation bar with icons
- Card-based product display
- Visual cart badge showing item count
- Responsive layout with scroll support

---

### **Session 2: Project Setup (Packages, Classes)**
✅ **Completed**

**Architecture Quality:**
- **MVC Pattern:** Clear separation of concerns
  - Models: `Product`, `CartItem`
  - Controllers: `ProductController`, `CartController`
  - Views: `MainWindow`, `ProductPage`, `CartPage`, `LoginPage`

**Naming Conventions:**
- ✅ PascalCase for classes: `ProductController`, `CartItem`
- ✅ camelCase for methods: `addToCart()`, `getTotalPrice()`
- ✅ camelCase for variables: `cartController`, `mainWindow`
- ✅ UPPER_CASE for constants: `PRIMARY_COLOR`, `SUCCESS_COLOR`
- ✅ Descriptive package names: `com.electronicstore.models`

**Design Patterns:**
- **Singleton Pattern:** Controllers use singleton to ensure single instance
- **Observer Pattern:** UI updates when cart changes
- **MVC Pattern:** Separation of data, logic, and presentation

---

### **Session 3: Main Window and Navigation**
✅ **Completed**

**Usability Features:**
- **Clear Navigation Flow:**
  - Home → Products Page
  - Products → Browse and add to cart
  - Cart → Review items, adjust quantities
  - Checkout → Login and complete order

**Navigation Enhancements:**
- Visual feedback on active page (highlighted button)
- Cart badge shows real-time item count
- Smooth page transitions using CardLayout
- Breadcrumb-style navigation

**HCI Principles:**
1. **Visibility of System Status**
   - Cart badge shows item count
   - Stock availability displayed
   - Total price always visible

2. **User Control and Freedom**
   - Easy navigation between pages
   - Quantity adjustment controls
   - Remove items with confirmation

3. **Consistency and Standards**
   - Uniform color scheme
   - Consistent button styles
   - Standard icons (🏠 🛒 💳)

4. **Error Prevention**
   - Form validation before submission
   - Confirmation dialogs for destructive actions
   - Clear error messages

5. **Recognition Rather Than Recall**
   - Icons with text labels
   - Tooltips on buttons
   - Visual product cards

6. **Aesthetic and Minimalist Design**
   - Clean white backgrounds
   - Ample spacing
   - No unnecessary elements

---

## 🎨 Color Scheme

```java
PRIMARY_COLOR   = #2980B9 (Blue)      // Main brand color
SECONDARY_COLOR = #3498DB (Light Blue) // Navigation
SUCCESS_COLOR   = #27AE60 (Green)     // Positive actions
ACCENT_COLOR    = #E74C3C (Red)       // Alerts/Remove
TEXT_COLOR      = #FFFFFF (White)     // Button text
BACKGROUND      = #ECF0F1 (Light Gray) // Page background
```

---

## 🚀 How to Run

### Method 1: Using Batch Script (Recommended)
```bash
run.bat
```

### Method 2: Using PowerShell
```powershell
powershell -ExecutionPolicy Bypass -File run.ps1
```

### Method 3: Quick Run
```bash
compile-and-run.bat
```

### Method 4: Manual Compilation
```bash
cd src
javac com/electronicstore/models/*.java com/electronicstore/controllers/*.java com/electronicstore/ui/*.java Main.java
java Main
```

---

## 📱 Application Features

### 1. **Product Catalog Page**
- Grid layout with product cards
- Product information: name, description, price, stock
- "Add to Cart" buttons with visual feedback
- Scrollable for many products
- 6 sample products included

### 2. **Shopping Cart Page**
- List of cart items with details
- Quantity adjustment (+/- buttons)
- Remove items with confirmation
- Real-time subtotal calculation
- Total price display
- Empty cart message when no items

### 3. **Login/Checkout Page**
- Professional login form
- Username and password fields
- Form validation
- "Remember me" option
- Registration link (placeholder)
- Order confirmation dialog

### 4. **Navigation System**
- Header with branding and search bar
- Navigation bar with 4 buttons
- Cart badge showing item count
- Active page highlighting
- Footer with contact information

---

## 🎓 HCI Principles Demonstrated

### ✅ **Visibility**
- All navigation options visible
- Cart count badge
- Stock availability
- Price information

### ✅ **Feedback**
- Button hover effects
- Success/error messages
- Visual confirmation dialogs
- Active page highlighting

### ✅ **Consistency**
- Uniform color scheme
- Standard button sizes
- Consistent fonts
- Same layout patterns

### ✅ **Error Prevention**
- Form validation
- Confirmation dialogs
- Clear error messages
- Disabled states when appropriate

### ✅ **User Control**
- Easy navigation
- Undo-like features (remove from cart)
- Clear exit points
- No forced paths

---

## 🧪 Testing the Application

### Test Credentials
- **Username:** `demo`
- **Password:** `demo`

### Test Scenarios

1. **Browse Products**
   - Click "Products" or "Home"
   - View 6 different electronic products
   - See prices and descriptions

2. **Add to Cart**
   - Click "Add to Cart" on any product
   - See confirmation message
   - Notice cart badge updates

3. **Manage Cart**
   - Click "Cart" button
   - Adjust quantities with +/- buttons
   - Remove items (with confirmation)
   - See total price update

4. **Checkout**
   - Click "Checkout" button
   - Enter credentials (demo/demo)
   - Confirm order
   - See success message

---

## 📊 Evaluation Criteria Met

### Session 1: Mock-up Design ✅
- ✅ Clear visual hierarchy
- ✅ Creative and modern design
- ✅ HCI principles applied (visibility, feedback, consistency)

### Session 2: Architecture ✅
- ✅ Proper package structure
- ✅ MVC pattern implementation
- ✅ Correct naming conventions
- ✅ Clean code organization

### Session 3: Navigation & Usability ✅
- ✅ Intuitive navigation flow
- ✅ Visual feedback on interactions
- ✅ Smooth page transitions
- ✅ User-friendly interface

---

## 🔧 Technical Details

### Technologies Used
- **Java SE** (Swing framework)
- **AWT** (Abstract Window Toolkit)
- **MVC Pattern**
- **Singleton Pattern**

### Key Classes

**Models:**
- `Product`: Represents electronic products
- `CartItem`: Represents items in shopping cart

**Controllers:**
- `ProductController`: Manages product data (Singleton)
- `CartController`: Manages cart operations (Singleton)

**Views:**
- `MainWindow`: Main application frame with navigation
- `ProductPage`: Product catalog display
- `CartPage`: Shopping cart management
- `LoginPage`: User authentication

---

## 📝 Future Enhancements (Sessions 4-8)

- Session 4: Product display interface improvements
- Session 5: Shopping cart calculations
- Session 6: User registration
- Session 7: Order history
- Session 8: Final testing and refinement

---

## 👥 Credits

**Project:** E-Commerce Interface Development  
**Theme:** Electronic Store  
**Framework:** Java Swing  
**Academic Year:** 2025-2026  
**University:** University of Algiers 1  
**Faculty:** Computer Science

