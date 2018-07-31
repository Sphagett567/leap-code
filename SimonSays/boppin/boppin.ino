// Declare binary columns as output pins
int one = 13;
int two = 12;
int four = 11;
int eight = 10;

// Setup pins as output
void setup() 
{
  pinMode(one, OUTPUT);
  pinMode(two, OUTPUT);
  pinMode(four, OUTPUT);
  pinMode(eight, OUTPUT);
}

// Loops continuosly
// Only shows 15
// Modify to count from 0 .. 255 (8 bits)
void loop() 
{
  lightNumber(15);
}

// Current is the current highest power of 2
void lightNumber(int n)
{
  blank();

  int current = 8;
  
  while (n > 0)
  {
    if (n - current < 0)
    {
      current /= 2;
    }

    else 
    {
      turnOn(n);
      n -= current;
    }
  }
}

// Only works for 0 .. 15 (4 bits)
void turnOn(int n)
{
 if (n >= 8)
  {
    digitalWrite(eight, HIGH);
  }
  else if (n >= 4)
  {
    digitalWrite(four, HIGH);
  }
  else if (n >= 2)
  {
    digitalWrite(two, HIGH);
  }
  else if (n >= 1)
  {
    digitalWrite(one, HIGH);
  }
}

// Blank all LEDs
void blank()
{
  // You need to fill this in
}

// Light up all LEDs
void lightAll()
{
  // You need to fill this in
}

