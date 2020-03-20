# FracZi

The source code for the original paid version is here.

FracZi is a true color fractal rendering app that allows custom formulas for the main equation and rendering of color in three color modes.

Using fracZi

When fracZi starts, it begins calculating exactly where it last closed. The first time fracZi starts, the default fractal is loaded. The fractal is displayed in stages on your screen. At each stage, the number of iterations and effective infinity is increased. FracZi displays the rendering progress at the top of your screen.

At any time, you can choose to zoom or pan your view of the fractal. To zoom or pan, draw a diagonal line with ONE of your fingers on the screen (fracZi will highlight the area for you). Once you lift your finger, fracZi will ask if you want to Zoom (in or out) or Center. If you select zoom, fracZi will begin calculating the fractal so that the area you selected fills the screen. If you select 'Center,' fracZi will begin calculating the fractal so that the area you selected is centered in the screen.

When you choose to zoom or center, fracZi displays a preview on your screen and immediately begins recalculating the fractal based on the new location. The preview image may appear pixilated until fracZi recalculates the pixels.

fracZi comes with several fractals to explore. To change the fractal you're exploring, use your device's menu button, and then select 'Open.' Choose a fractal from those displayed.

in addition to the fractals provided, any fractals you have saved will be available to load. Once your fractal is loaded, you can open the 'Custom' screen to access the formulas and rendering details.

fracZi allows you to control many aspects of the rendering process. You can enter formulas for the main equation, seeds, infinity, iterations, red, green, blue, hue, saturation, balance (brightness), cyan, magenta, yellow, and black. Images can be shared and/or saved as a PNG file.

Formulas are constructed of tokens and tokens can either be an operator (=,-,*,/,^) or an operand. Operands can be literal numbers, variables, functions, or even sub-formulae.

Supported variables are:

Z (The complex number that is fed back into the equation. Z= A + Bi)
A (the real part of Z)
B (the imaginary part of Z)
C (the current position in the x, y coordinate system C= X + Yi)
X (the real part of C)
Y (the imaginary part of C)
N (Number of iterations before breaking out of the loop)
P (the total number of iteration allowed before breaking out of the loop)
M (the maximum infinity)
I (the current infinity)
For instance, this formula

Z * Z + C

Produces the standard Mandelbrot image. If we used this formula for red

Red = N / P

We would see the Mandelbrot set because when N = P, the maximum iterations were reached. In this case, N/P = 1, the maximum amount of red is produced. In areas where infinity was reached quickly (say in 2 iterations out of 100 possible), the amount of red would be lower because N / P = 2 /100 = .02.


All color attributes are rendered based on a scale from 0 to 1. You can use this fact with your formulas to produce very colorful images.

Formulas also support functions. The allowed functions are

LOG
EXP
SIN
COS
TAN
ATAN
COM
ABS
MAX
MIN
BOUND
WRAP
When using a function in your formula, precede the function name with an ampersand (@). E.g. @SIN(Z)

Order of Operation

FracZi evaluates your expression from left to right. The equation

Z = 2 * Z ^ 3 + C

May not give the results you expect. Because of the left to right order of operations, 2 will be multiplied by Z and that result raised to the power of 3. fracZi recognizes parenthesis. So in this case, probably the equation you want is

Z = 2 * (Z ^ 3) + C

Please remember this.

Let's compose a formula for the 'Burning Ship' fractal. You can see more about this fractal here ->The Burning Ship

The Burning Ship fractal is very close to Mandelbrot's famous equation (Z = Z^2+c), except the absolute value of real and imaginary parts of Z are used instead of their raw values. Often the equation is written

Z = (|Re(z)| + |Im(z)| * i) ^2 + C

To enter this formula into fracZi, we can just use the variables A and B in place of Re(z) and Im(z) because fracZi gives us these for convenience (and speed). However, fracZi does not give us the absolute value of A and B, so we will have to deal with absolute value. We also have one more area to address. The formula above multiplies Im(z) by i. The end effect is a new complex number where the absolute values of the real and imaginary parts are used. In fracZi, we can create a new complex number with the COM function. The COM function takes the real part and the imaginary part as parameters.

So:

Z = @COM( @ABS(A), @ABS(B) ) ^ 2 + C

Entering this formula into fracZi will produce the "Burning Ship" fractal.


Working with Color

fracZi supports three color modes; RGB (red, green, blue), HSB (Hue, Saturation, and Brightness), and CYMK (Cyan, Yellow, Magenta, and Black).

Regardless of the mode you select, each attribute in the mode accepts a decimal value between 0 and 1. 0 is none and 1 is full. The simplest technique is to divide a number returned from the iteration loop by the maximum that the number could achieve. An obvious choice is iterations. Specifically, if we divide the number of iterations that occurred by the total possible Iterations, we achieve a ratio that can be used to apply color. We could also compare either the real or imaginary parts of Z ( A and B) to infinity. For instance,

Red = N / P
Green = A / M
Blue = B / M
Or even better

Red = N / P
Green = @ABS(A / M)
Blue = @ABS(B / M)
The final color will be a mix of red, green, and blue in the ratios returned from the equations. Red would indicate how many iterations occurred, green would indicate how the A part of the result (the real part) relates to infinity, and blue would indicate how the B part of the result (the imaginary part) relates to infinity. We can also use the functions in the color rendering formulas too. E.g. Red = SIN(A)

Additionally, we can combine elements for the formula

Red = @ABS( (A + B) / (M * 2))

The same concept applies to HSB. For hue, 0 = 0 degrees, and 1 = 360 degrees. For saturation, 0 is no saturation and 1 is full saturation, etcÂ…

Although the color attributes expect a decimal between 0 and 1, values outside of this range are acceptable. For instance, you could set red to A / B. Sometimes, either A or B are negative and A could be larger than B. The end result is you could be setting the RED component to a negative number or a number larger than 1. The system does allow this; however, you could use the functions in fracZi to further control the output. Useful functions here are

MAX

MIN

BOUND

WRAP

For instance, the equation RED = @MIN(A/B,1), will return 1 if A/B is greater than 1.

The equation RED = @BOUND(a/b,0,1), will return A/B if A/B is between 0 and 1; otherwise the equation will return 0 if A/B is less than 0 and 1 if A/B is greater than 1.

The equation RED = @WRAP(a/b,0,1), will return A/B if A/B is between 0 and 1; otherwise the equation will wrap the result around 0 and 1. So if A/B = 1.2 the return value will be .20. If a/b = -9, the function will return 1.

@WRAP(2.3,0,1.5) will return .8

Iterations and Infinity

Calculating fractals is processor intensive. fracZi attempts to give you a view of the fractal as soon as possible. This is achieved by multiple renders. With each render, the maximum iterations and infinity is increased. You can control the rate of increase on the 'Custom' screen of fracZi. This feature is controlled with six parameters.

Starting Infinity

MAX INFINITY

Infinity Increment Equation

Starting Iterations

MAX ITERATIONS

Iteration Increment Equation

fracZi continues to render the fractal as long as Iterations < Max iterations and Infinity < Max infinity. After each render, fracZi will adjust infinity and iterations based on the formulae in the Increment Equation fields.

For instance, suppose we enter 4 for the starting infinity, 250,000 for the Max Infinity, and M * 2 for the infinity increment equation. When we click on the render button, fracZi will render the fractal with an infinity of 4. When complete, fracZi evaluates the increment equation which says to take the current infinity and multiply it by 2. fracZi does this and renders the next fractal with an infinity of 8. The next time 16, then 32,64,etcÂ… Once infinity reaches or passes 250,000, fracZi stops rendering. The same paradigm applies to Iterations. fracZi starts using the Start Iterations value, in between each render, fracZi adjust iterations per the Iteration Increment equation and continues until Iterations > Max Iterations.

Help, I want to understand this
Imagine you were given this math problem and asked to solve for X

X ^ 2 = -9

Solving for X

X = sqrt(-9)

This is where the fun begins. The square root of a number is a number when multiplied by itself, produces the desired number. A number does not exist when multiplied by itself produces a negative number.

Although, this was first observed in about 50 CE by the Greek Mathematician, Heron of Alexandria, the concept was not furthered for another 1500 years. The opinion of many mathematicians (at the time), was that these numbers were useless. Around 300 CE, the Greek mathematician, Diophantus, contemplated the equation 4x + 20 = 0, and determined the idea of x being a negative number was preposterous.

Around 1572 CE, Rafael Bombelli published rules for dealing with negative numbers. At first, the square root of -1 was called Imaginary, in a derogatory way, but eventually, the concept took hold (because in math, it really worked). It seems true that negative numbers are imaginary. They don't really exist in the physical world. However, negative numbers help us create abstractions to solve problems. Negative numbers are positive amounts (a magnitude) with a direction.

In fracZi, all numbers are complex (having a real and imaginary part). In math, complex numbers are written as a + bi. 'a' is the real part, and 'b' is the imaginary part. 'b', the imaginary part, represents how many square roots of -1 are part of the number. i equals the square root of -1.

If you think of this a + bi, when you encountered the square root (or any root) of a negative number, a + bi is the simplest form to represent the number.

Let's look back at the first equation we contemplated (X ^ 2 = -9). If we solve for x

X = sqrt(-9)

X = sqrt (9) + sqrt (-1)

Or

X = 3 + 1i

Or

X = 3 + i X is a complex number because it has an imaginary part (the + I or +1i).

In 1918, the French mathematician, Julia, gained famed for his description of iterating a rational function. Julia's research was the first to offer a glimpse of the visual representation of iterating these types of equations.

Later, Benoit Mandelbrot came around and applied Julia's work using computers. Mandelbrot plotted all of the Julia sets in a plane. He noted, that in the result of iterating the function was either contained (approaches 0) or headed into infinity.

Specifically, Mandelbrot brought us this image


Which plots where the function tends to resolve (not go to infinity). In the image, the red areas are where the function resolves and outside the red, the function tends toward infinity. These types of fractals are based on Chaos. We could call points outside the Mandelbrot set CHOATIC, and we could further measure, how chaotic they are by how quickly the result moves towards infinity.

Lets look at the basic Mandelbrot equation and walkthrough calculating a fractal.

Z = Z * Z + C

When we calculate a fractal, it is displayed on a grid (X,Y). The grid location is part of the equation above, remember C = X + Yi. The pixel drawn at each location can depend on many factors, but the most common is the number of iterations required to pass a certain predefined number that is considered infinite. We can set this infinity to whatever we want. In essence, we are sampling the fractal. The higher our infinity and number of iterations, the most precise our sample is.

We can get a good impression of the Mandelbrot set with an infinity of 4.

So lets iterate this equation and see how long until the output exceeds 4.

Lets also assume that we are drawing this fractal for X between -2 and 2 and Y between -2 and 2.

So, for our first point, x= -2 and y=-2 or C = -2 -2i

We need one more parameter to begin calculating the fractal. We need to decide when to stop checking for Infinity because some points will NEVER reach infinity (these points are part of the Mandelbrot set). Lets assume that we will only try 4 iterations to reach infinity.

Also, to begin, Z = 0 + 0i or 0.

First iteration at -2,-2

Z = Z *Z + C.

Z = 0 * 0 + -2 - 2i.

Z = -2 - 2i.

This is the result of the first iteration. When we evaluate infinity, we consider negative Infinity as well as positive infinity. Or the absolute value of either A or B must be less than infinity to continue. So is the A or B part of Z greater than positive infinity or less than negative infinity?.

Z = a + bi

Z= -2 -2i

So

A = -2

B = -2

NO. We set infinity to 4, we are still in range, so the next iteration begins. For the subsequent iterations, the result of the last iteration is fed back into the equation for Z.

For the second iteration, Z = -2 -2i, which was the output of the previous iteration.

Second Iteration at -2, -2

Z = Z * Z + C

Z = (-2 - 2i) * (-2 - 2i) + (-2 - 2i)

Or

Z = -2+6i

In this iteration, the b part is equal to 6, which is over our infinity of 4. The number of iterations is 2. Our max iterations is 4. So if we use the equation N / P for RED, we get

RED = N / P

RED = 2 / 4

RED = .5

The point -2, -2 will be colored with a shade that consists of RED at half intensity.

Lets look at another point in the plane. Lets calculate 0,0..

So, Iteration 1 at 0,0.

Z = Z *Z + C

Z = 0 * 0 + 0 + 0i

Z = 0

So, Iteration 2 at 0,0

Z = Z *Z + C.

Z = 0 * 0 + 0 + 0i

Z = 0

So, Iteration 3 at 0,0

Z = Z *Z + C.

Z = 0 * 0 + 0 + 0i

Z = 0

So, Iteration 4 at 0,0

Z = Z *Z + C

Z = 0 * 0 + 0 + 0i

Z = 0

After four iterations, we still haven't broke out of the loop because A and B (the parts of the complex number Z), are still below infinity. But we also break out of the calculation if our max iterations is achieved. So, at this point, we stop

The number of iterations is 4. Our max iterations is 4. So if we use the equation N / P for RED, we get

RED = N / P
RED = 4 / 4
RED = 1

This is full strength RED

fracZi does exactly this. FracZi iterates your equation for every point on your screen and draws the color according to your formulas.


This fractal was calculated using X * X + C and N/P for Red. Blue and green had 0 (the number zero) for the formula. The iterations was 24 and infinity was 4.
There are many other references on the web about complex numbers, imaginary numbers, and fractals. A little algebra helps with complex numbers. If you can imagine a complex number as a vector, then geometry really comes in handy.
