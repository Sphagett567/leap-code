// Declare binary columns as output pins
int one = 13;
int two = 12;
int four = 11;
int eight = 10;

int sixteen = 9;
int thrirty_two = 8;
int sixty_four = 7;
int hundred = 6;

// Setup pins as output
void setup() 
{
  pinMode(one, OUTPUT);
  pinMode(two, OUTPUT);
  pinMode(four, OUTPUT);
  pinMode(eight, OUTPUT);
  pinMode(sixteen, OUTPUT);
  pinMode(thrirty_two, OUTPUT);
  pinMode(sixty_four, OUTPUT);
  pinMode(hundred, OUTPUT);
}

// Loops continuosly
// Only shows 15
// Modify to count from 0 .. 255 (8 bits)
void loop() 
{
  for (int i = 0; i <= 255; i++) {
    lightNumber(i);
    delay(100);
  }
  delay(1000);
}

// Current is the current highest power of 2
void lightNumber(int n)
{
  blank();

  int current = 128;
  
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
  if (n >= 128) {
    digitalWrite(hundred, HIGH);
  } else if (n >= 64) {
    digitalWrite(sixty_four, HIGH);
  } else if (n >= 32) {
    digitalWrite(thirty_two, HIGH);
  } else if (n >= 16) {
    digitalWrite(sixteen, HIGH);
  }else if (n >= 8)
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
  for (int i = 13; i >= 6; i--) {
    digitalWrite(i, LOW);
  }
}

// Light up all LEDs
void lightAll()
{
   for (int i = 13; i >= 6; i--) {
    digitalWrite(i, HIGH);
  }
}

