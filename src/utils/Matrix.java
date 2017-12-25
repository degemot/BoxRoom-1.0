package utils;

public class Matrix {
    public float[] matrix= new float[4*4];

    public Matrix(){
        matrix[0]= 1;
        matrix[5]= 1;
        matrix[10]= 1;
        matrix[15]= 1;
    }

    public void setIdentity(){
        matrix= new float[4*4];
        matrix[0]= 1;
        matrix[5]= 1;
        matrix[10]= 1;
        matrix[15]= 1;
    }

    public void translate(Vec3 pos) {
        translate(pos.x,pos.y,pos.z);
    }

    public void translate(float x,float y,float z){
        matrix[12]+= x;
        matrix[13]+= y;
        matrix[14]+= z;
    }

    public void setPerspective(float fovy, float aspect, float zNear, float zFar, boolean zZeroToOne) {
        float h = (float) Math.tan(fovy * 0.5f);
        matrix[0]=1.0f / (h * aspect);
        matrix[5]=1.0f / h;
        boolean farInf = zFar > 0 && Float.isInfinite(zFar);
        boolean nearInf = zNear > 0 && Float.isInfinite(zNear);
        if (farInf) {
            // See: "Infinite Projection Matrix" (http://www.terathon.com/gdc07_lengyel.pdf)
            float e = 1E-6f;
            matrix[10]=e - 1.0f;
            matrix[14]=(e - (zZeroToOne ? 1.0f : 2.0f)) * zNear;
        } else if (nearInf) {
            float e = 1E-6f;
            matrix[10]=(zZeroToOne ? 0.0f : 1.0f) - e;
            matrix[14]=((zZeroToOne ? 1.0f : 2.0f) - e) * zFar;
        } else {
            matrix[10]=(zZeroToOne ? zFar : zFar + zNear) / (zNear - zFar);
            matrix[14]=(zZeroToOne ? zFar : zFar + zFar) * zNear / (zNear - zFar);
        }
        matrix[11]=-1.0f;
    }

    public void setLookAt(float eyeX, float eyeY, float eyeZ,
                     float centerX, float centerY, float centerZ,
                     float upX, float upY, float upZ) {
        // Compute direction from position to lookAt
        float dirX, dirY, dirZ;
        dirX = eyeX - centerX;
        dirY = eyeY - centerY;
        dirZ = eyeZ - centerZ;
        // Normalize direction
        float invDirLength = 1.0f / (float) Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
        dirX *= invDirLength;
        dirY *= invDirLength;
        dirZ *= invDirLength;
        // left = up x direction
        float leftX, leftY, leftZ;
        leftX = upY * dirZ - upZ * dirY;
        leftY = upZ * dirX - upX * dirZ;
        leftZ = upX * dirY - upY * dirX;
        // normalize left
        float invLeftLength = 1.0f / (float) Math.sqrt(leftX * leftX + leftY * leftY + leftZ * leftZ);
        leftX *= invLeftLength;
        leftY *= invLeftLength;
        leftZ *= invLeftLength;
        // up = direction x left
        float upnX = dirY * leftZ - dirZ * leftY;
        float upnY = dirZ * leftX - dirX * leftZ;
        float upnZ = dirX * leftY - dirY * leftX;

        this.matrix[0]=leftX;
        this.matrix[1]=upnX;
        this.matrix[2]=dirX;
        this.matrix[3]=0.0f;
        this.matrix[4]=leftY;
        this.matrix[5]=upnY;
        this.matrix[6]=dirY;
        this.matrix[7]=0.0f;
        this.matrix[8]=leftZ;
        this.matrix[9]=upnZ;
        this.matrix[10]=dirZ;
        this.matrix[11]=0.0f;
        this.matrix[12]=-(leftX * eyeX + leftY * eyeY + leftZ * eyeZ);
        this.matrix[13]=-(upnX * eyeX + upnY * eyeY + upnZ * eyeZ);
        this.matrix[14]=-(dirX * eyeX + dirY * eyeY + dirZ * eyeZ);
        this.matrix[15]=1.0f;
    }

    public void scale(float size){
        matrix[0]=size;
        matrix[5]=size;
        matrix[10]=size;
    }

    public void rotate(Vec3 angle){
        float rX= (float) Math.toRadians(angle.x);
        float rY= (float) Math.toRadians(angle.y);
        float rZ= (float) Math.toRadians(angle.z);

        Matrix rotate= new Matrix();

        rotate.multiply(rotateZ(rZ));
        rotate.multiply(rotateX(rX));
        rotate.multiply(rotateY(rY));
        rotate.multiply(this);

        matrix= rotate.matrix;
    }

    public Matrix rotateZ(float radiantAngleZ) {
        float cos= (float) Math.cos(radiantAngleZ);
        float sin= (float) Math.sin(radiantAngleZ);

        Matrix matrixZ= new Matrix();

        matrixZ.matrix[0]= cos;
        matrixZ.matrix[1]= -sin;    //maybe conversely
        matrixZ.matrix[4]= sin;    //maybe conversely
        matrixZ.matrix[5]= cos;

        return matrixZ;
    }

    public Matrix rotateX(float radiantAngleX) {
        float cos= (float) Math.cos(radiantAngleX);
        float sin= (float) Math.sin(radiantAngleX);

        Matrix matrixX= new Matrix();

        matrixX.matrix[5]= cos;
        matrixX.matrix[6]= -sin;
        matrixX.matrix[9]= sin;
        matrixX.matrix[10]= cos;

        return matrixX;
    }

    public Matrix rotateY(float radiantAngleY) {
        float cos= (float) Math.cos(radiantAngleY);
        float sin= (float) Math.sin(radiantAngleY);

        Matrix matrixY= new Matrix();

        matrixY.matrix[0]= cos;
        matrixY.matrix[2]= sin;
        matrixY.matrix[8]= -sin;
        matrixY.matrix[10]= cos;

        return matrixY;
    }

    public void multiply(Matrix matrix) {
        Matrix result= new Matrix();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += this.matrix[x + e * 4] * matrix.matrix[e + y * 4];
                }
                result.matrix[x + y * 4] = sum;
            }
        }
        this.matrix= result.matrix;
    }


    public Matrix multiplyR(Matrix matrix) {
        Matrix result= new Matrix();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += this.matrix[x + e * 4] * matrix.matrix[e + y * 4];
                }
                result.matrix[x + y * 4] = sum;
            }
        }
        return result;
    }

    public Vec3 multiplyR(Vec3 vec,float vecW){
        float x= vec.x*matrix[0]+vec.y*matrix[4]+vec.z*matrix[8]+vecW*matrix[12];
        float y= vec.x*matrix[1]+vec.y*matrix[5]+vec.z*matrix[9]+vecW*matrix[13];
        float z= vec.x*matrix[2]+vec.y*matrix[6]+vec.z*matrix[10]+vecW*matrix[14];
        float w= vec.x*matrix[3]+vec.y*matrix[7]+vec.z*matrix[11]+vecW*matrix[15];

        Vec3 result= new Vec3(x/w,y/w,z/w);
        return result;
    }

    public Matrix invertR(){
        Matrix invert= new Matrix();

        float s0= matrix[0]*matrix[5]-matrix[4]*matrix[1];
        float s1= matrix[0]*matrix[6]-matrix[4]*matrix[2];
        float s2= matrix[0]*matrix[7]-matrix[4]*matrix[3];
        float s3= matrix[1]*matrix[6]-matrix[5]*matrix[2];
        float s4= matrix[1]*matrix[7]-matrix[5]*matrix[3];
        float s5= matrix[2]*matrix[7]-matrix[6]*matrix[3];

        float c5= matrix[10]*matrix[15]-matrix[14]*matrix[11];
        float c4= matrix[9]*matrix[15]-matrix[13]*matrix[11];
        float c3= matrix[9]*matrix[14]-matrix[13]*matrix[10];
        float c2= matrix[8]*matrix[15]-matrix[12]*matrix[11];
        float c1= matrix[8]*matrix[14]-matrix[12]*matrix[10];
        float c0= matrix[8]*matrix[13]-matrix[12]*matrix[9];

        // Should check for 0 determinant

        float invdet= 1/(s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);

        invert.matrix[0]= (matrix[5] * c5 - matrix[6] * c4 + matrix[7] * c3) * invdet;
        invert.matrix[1]= (-matrix[1] * c5 + matrix[2] * c4 - matrix[3] * c3) * invdet;
        invert.matrix[2]= (matrix[13] * s5 - matrix[14] * s4 + matrix[15] * s3) * invdet;
        invert.matrix[3]= (-matrix[9] * s5 + matrix[10] * s4 - matrix[11] * s3) * invdet;

        invert.matrix[4]= (-matrix[4] * c5 + matrix[6] * c2 - matrix[7] * c1) * invdet;
        invert.matrix[5]= (matrix[0] * c5 - matrix[2] * c2 + matrix[3] * c1) * invdet;
        invert.matrix[6]= (-matrix[12] * s5 + matrix[14] * s2 - matrix[15] * s1) * invdet;
        invert.matrix[7]= (matrix[8] * s5 - matrix[10] * s2 + matrix[11] * s1) * invdet;

        invert.matrix[8]= (matrix[4] * c4 - matrix[5] * c2 + matrix[7] * c0) * invdet;
        invert.matrix[9]= (-matrix[0] * c4 + matrix[1] * c2 - matrix[3] * c0) * invdet;
        invert.matrix[10]= (matrix[12] * s4 - matrix[13] * s2 + matrix[15] * s0) * invdet;
        invert.matrix[11]= (-matrix[8] * s4 + matrix[9] * s2 - matrix[11] * s0) * invdet;

        invert.matrix[12]= (-matrix[4] * c3 + matrix[5] * c1 - matrix[6] * c0) * invdet;
        invert.matrix[13]= (matrix[0] * c3 - matrix[1] * c1 + matrix[2] * c0) * invdet;
        invert.matrix[14]= (-matrix[12] * s3 + matrix[13] * s1 - matrix[14] * s0) * invdet;
        invert.matrix[15]= (matrix[8] * s3 - matrix[9] * s1 + matrix[10] * s0) * invdet;

        return invert;
    }
}
