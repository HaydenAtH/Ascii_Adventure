package Renderer;


public class Vector2
{
    public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;	//Multiply an angle in degrees by this constant to convert it to radians.
    public static float TO_DEGREES = (1 / (float) Math.PI) * 180;	//Multiply an angle in radians by this constant to convert it to degrees.
    public float x, y;	//Stores the x and y components of the 2d vector.

    /** Contructs a 2d vector with an x and y component of zero. */
    public Vector2()
    {
    }

    /** Contructs a 2d vector with given x and y components. */
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    /** Constructs a vector using another vector's x and y components. */
    public Vector2(Vector2 other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    /** Sets the x and y components of the vector. Returns the instance of this vector for easy chaining. */
    public Vector2 set(float x, float y)
    {
        //Updates the components of the vector.
        this.x = x;
        this.y = y;

        //Returns a reference to this vector to perform operations on it with chained method calls.
        return this;
    }

    /** Copies the x and y components of another vector into this vector. Returns the instance of this vector for chained operations. */
    public Vector2 set(Vector2 other)
    {
        //Copies the x and y components of the vector argument into this vector.
        this.x = other.x;
        this.y = other.y;

        //Returns the instance of this vector in order to apply multiplications to it via chained method calls.
        return this;
    }

    /** Adds the given amount to the x and y components of this Vector. Returns a reference of this vector to use in method chaining for multiple operations. */
    public Vector2 add(float amount)
    {
        //Adds the given amount to the vector's x and y components.
        this.x += amount;
        this.y += amount;

        //Returns a reference to this Renderer.Vector2.
        return this;
    }

    /** Adds the x and y arguments to the x and y components of this Vector. Returns a reference of this vector to use in method chaining for multiple operations. */
    public Vector2 add(float x, float y)
    {
        //Adds the x and y values passed in to the vector's x and y components.
        this.x += x;
        this.y += y;

        //Returns a reference to this Renderer.Vector2.
        return this;
    }

    /** Adds a vector to this vector. Done by adding the x and y components of the other vector to this vector. Returns the instance of this vector for use in operation chaining. */
    public Vector2 add(Vector2 other)
    {
        //Adds the x and y components of the other vector to this vector.
        this.x += other.x;
        this.y += other.y;

        //Returns a reference to this Renderer.Vector2.
        return this;
    }

    /** Subtracts the given amount from the x and y components of this Vector. Returns a reference of this vector to use in method chaining for multiple operations. */
    public Vector2 sub (float amount)
    {
        //Subtracts the given amount from the vector's x and y components.
        this.x -= amount;
        this.y -= amount;

        //Returns a reference to this Renderer.Vector2.
        return this;
    }

    /** Subtracts the x and y arguments from the x and y components of this Vector. Returns a reference of this vector to use in method chaining for multiple operations. */
    public Vector2 sub(float x, float y)
    {
        //Subtracts the x and y arguments from this vector's x and y components.
        this.x -= x;
        this.y -= y;

        //Returns a reference to this Renderer.Vector2.
        return this;
    }

    /** Adds a vector form this vector. Done by subtracting the x and y components of the other vector from this vector's x and y components. Returns the instance of this vector
     *  for use in operation chaining. */
    public Vector2 sub(Vector2 other)
    {
        //Takes this vector's x and y components, and subtracts them by the other vector's x and y components.
        this.x -= other.x;
        this.y -= other.y;

        //Returns a reference to this Renderer.Vector2 for use in operation chaining via method chaining.
        return this;
    }

    /** Multiplies this vector by a scalar. Done by multiplying the x and y components of this vector by the given argument. Returns the instance of this vector for use in
     *  operation chaining. */
    public Vector2 mul(float scalar)
    {
        //Multiplies the x and y components of this vector by the scalar argument.
        this.x *= scalar;
        this.y *= scalar;

        //Returns a reference to this Renderer.Vector2 for use in operation chaining via method chaining.
        return this;
    }

    /** Returns the magnitude of this vector. */
    public float magnitude()
    {
        //Returns the magnitude of the vector via the pythagorean theorem (i.e., sqrt(x*x + y*y)). Note that the FloatMath.sqrt() method is faster than the Math.sqrt() method.
        return (float)Math.sqrt(x*x + y*y);
    }

    /** Normalizes the vector. This means that we convert this vector into a unit vector with magnitude one. We return a reference to the normalized vector for use in chaining
     *  operations via chained method calls. */
    public Vector2 normalize()
    {
        //Stores the magnitude of the vector.
        float magnitude = magnitude();

        //If the magnitude is not zero, normalize the vector. Otherwise, the vector is zero and does not need normalizing. Prevents an ArithmeticException.
        if(magnitude != 0)
        {
            //Normalizes the vector by dividing its x and y components by its magnitude.
            this.x /= magnitude;
            this.y /= magnitude;
        }

        //Returns a reference to this Renderer.Vector2 for use in operation chaining via method chaining.
        return this;
    }


    /** Returns the distance between this vector and another vector. Note that the value returned is a scalar quantity. */
    public float distance(Vector2 other)
    {
        //Finds the difference in x and y components between this vector and the other vector. This finds the vector which points from the other vector to this vector.
        //It wouldn't make a difference if we reversed the subtraction, as the magnitude of the distance vector would remain the same.
        float distX = this.x - other.x;
        float distY = this.y - other.y;

        //Returns the magnitude of the distance between both vectors. This is done via the pythagorean theorem, using sqrt(x*x + y*y).
        return (float)Math.sqrt(distX*distX + distY*distY);
    }

    /** Returns the distance between this vector and the given x and y components, which form another vector. Note that the value returned is a scalar distance quantity. */
    public float distance(float x, float y)
    {
        //Finds the x and y distance between both vectors by subtracting their x and y components. Note which vector is subtracted by which other vector is irrelevent,
        //since the magnitude of the resulting vector will be the same.
        float distX = this.x - x;
        float distY = this.y - y;

        //Returns the magnitude of the distance between both vectors. This is done via the pythagorean theorem, using sqrt(x*x + y*y).
        return (float)Math.sqrt(distX*distX + distY*distY);
    }

    /** Returns the distance squared between this vector and another vector. Note that this avoids using a square root, making it more cost-efficient. Should be used
     *  instead of the Renderer.Vector2.dist() method, as it prevents the costly square-root function. */
    public float distSquared(Vector2 other)
    {
        //Finds the x and y distance between both vectors by subtracting their x and y components. Note which vector is subtracted by which other vector is irrelevent,
        //since the magnitude of the resulting vector will be the same.
        float distX = this.x - other.x;
        float distY = this.y - other.y;

        //Returns the distance square between both vectors. This is done via the pythagorean theorem, using sqrt(x*x + y*y). However, we return the distance squared
        //by avoiding the square root. This is cost-efficient.
        return distX*distX + distY*distY;
    }

    /** Returns the distance squared between this vector and another vector with position (x,y). Note that this avoids using a square root, making it more cost-efficient.
     *  Should be used instead of the Renderer.Vector2.dist() method, as it prevents the costly square-root function. */
    public float distSquared(float x, float y)
    {
        //Finds the x and y distance between both vectors by subtracting their x and y components. Note which vector is subtracted by which other vector is irrelevent,
        //since the magnitude of the resulting vector will be the same.
        float distX = this.x - x;
        float distY = this.y - y;

        //Returns the distance square between both vectors. This is done via the pythagorean theorem, using sqrt(x*x + y*y). However, we return the distance squared
        //by avoiding the square root. This is cost-efficient.
        return distX*distX + distY*distY;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}