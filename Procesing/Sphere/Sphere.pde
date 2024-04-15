import peasy.*;

PeasyCam cam;

void drawSphere(int res, float radius)
{
  for(int i = 0; i < res; i++)
  {
   float theta = map(i, 0, res, -PI, PI);
   for(int j = 0; j < res; j++)
   {
     float fi = map(j, 0, res, -PI, PI);
     
     float x = radius * sin(fi) * cos(theta);
     float y = radius * sin(fi) * sin(theta);
     float z = radius * cos(fi);
     
     stroke(255);
     strokeWeight(5);
     point(x, y, z);
     
   }
  }
}

void setup()
{
  size(800, 800, P3D);
  cam = new PeasyCam(this, 200);
}

void draw()
{
  background(0);
  
  lights();
  
  drawSphere(50, 100);
}
