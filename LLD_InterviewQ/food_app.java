class FoodDeliveryApp {
vector restaurants;
vector users;
vector deliveryPersons;
}

class Restaurant {
string restaurantId;
string restaurantName;
Address location;
Rating rating;
FoodMenu menu;

public:
	bool register(FoodDeliveryApp& app);
	FoodMenu getMenu();
}

class Address {
int zipCode;
string street;
string city;
string state;
string country;
}

class Rating {
double rating;
public:
bool updateRating(double newRating);
// consider newRating to calculate new Average Rating
double getRating();
}

class FoodMenu {
set items;
public:
set getFoodMenu();
bool addItem(Item item);
bool updateItem(Item oldItem, Item newItem);
bool deleteItem(Item item);
set getMenuByCuisine(Cuisine cuisine);
set getMenuByMealType(MealType mealType);
}

class Item {
int itemId;
string itemName;
string itemDescription;
Cuisine cuisine;
MealType mealType;
double price;
}

enum class Cuisine {
NORTH_INDIAN, SOUTH_INDIAN;
}

enum class MealType {
BREAK_FAST, LUNCH, SNACKS, DINNER;
}

class Account {
string firstName;
string lastName;
string email;
string userName;
string passWord;
}
class User {
searchObj search;
bool createAccount(Account account);
}

class Member : Public User {
Account account;
Cart cart;
vector order;

bool update(Account account);
bool delete(Account account);
Account get();
vector<Order> getAllOrders();
}

class searchObj {
vector searchRestaurants(string city);
vector searchRestaurants(string city, string restaurantName);
}

class Cart {
vector items;
double cartValue;
Payment payment;

bool addItem(Item item);
bool removeItem(Item item);
vector<Item> getAllItems();
void applyCoupon(Coupon coupon);
double calculateCartValue();
}

class Coupon {
string code;
string description;
}

class Order {
int orderId;
OrderStatus orderStatus;

bool placeOrder(Member member, Cart cart);
bool cancelOrder();
}

enum class OrderStatus {
SUCCESS, PENDING, CANCELLED, OUT_FOR_DELIVERY, DELIVERED;
}

class Payment {
bool makePayment();
}

class creditCardPayment : Public Payment {
bool makePayment();
}

class debitCardPayment : Public Payment {
bool makePayment();
}

class UPIPayment : Public Payment {
bool makePayment();
}

class Invoice {
int invoiceId;
double amount;
Order order;
PaymentMode mode;
TaxCalculation taxCal;
}

enum class PaymentMode {
CREDIT_CARD, DEBIT_CARD, UPI;
}

class TaxCalculation {
double cgstCharge;
double sgstCharge;
double serviceCharge;

double getTaxCalculated(double amount);
}

class DeliveryBoy : Public User {
Account account;
vector deliveries;

vector<Delivery> getAllDeliveries();
}

class Delivery {
int deliveryId;
Restaurant restautant;
Member member;
Rating deliveryRating;
Address dropLocation;
DateAndTime pickUpTime;
DateAndTime deliverydate;
OrderStatus orderStatus;
}